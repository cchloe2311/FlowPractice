package com.example.flowpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private val scope = MainScope() // Creates the main CoroutineScope for UI components. The resulting scope has SupervisorJob and Dispatchers.Main context elements.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flowPracticeRemoteDataSource = FlowPracticeRemoteDataSource()
        val flowPracticeRepository = FlowPracticeRepository(flowPracticeRemoteDataSource)

        scope.launch {
            flowPracticeRepository.count.collect { // collect는 반드시 코루틴이나 다른 suspend function에서 호출되어야 함
                tv_count.text = it
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        scope.cancel()
    }
}
