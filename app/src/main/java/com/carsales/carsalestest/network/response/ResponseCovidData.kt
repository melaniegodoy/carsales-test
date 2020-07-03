package com.carsales.carsalestest.network.response

data class ResponseCovidData (
    val data : CovidData
)

data class CovidData (
    val confirmed : String,
    val active : String
)