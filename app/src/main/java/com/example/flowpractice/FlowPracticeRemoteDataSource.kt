package com.example.flowpractice

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlowPracticeRemoteDataSource(private val refreshIntervalMs: Long = 5000) {

    private var count = 0

    val countFlow: Flow<Int> = flow {
        while(true) {
            emit(count++) // Emits the result of the request to the flow
            delay(refreshIntervalMs) // call suspend function
        }
    }
}