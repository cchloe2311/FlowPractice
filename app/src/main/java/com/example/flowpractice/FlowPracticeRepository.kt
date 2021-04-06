package com.example.flowpractice

import kotlinx.coroutines.flow.map

class FlowPracticeRepository(private val flowPracticeRemoteDataSource: FlowPracticeRemoteDataSource) {

    val count = flowPracticeRemoteDataSource.countFlow.map { value -> "its value is $value" }
}