package com.systems.testtask.data.remoteDataClass

data class SeachResults(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)