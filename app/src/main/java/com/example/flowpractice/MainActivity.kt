package com.example.flowpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flowPracticeRemoteDataSource = FlowPracticeRemoteDataSource()
        val flowPracticeRepository = FlowPracticeRepository(flowPracticeRemoteDataSource)

        // TODO GlobalScope를 쓰는게 맞나?
        GlobalScope.launch(Dispatchers.Main) { // if no Dispatchers.Main -> CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
            flowPracticeRepository.count.collect { // collect는 반드시 코루틴이나 다른 suspend function에서 호출되어야 함
                tv_count.text = it
            }
        }
    }
}
