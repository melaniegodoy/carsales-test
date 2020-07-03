package com.carsales.carsalestest.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.carsales.carsalestest.network.api.Api
import com.carsales.carsalestest.network.api.ApiHeader
import com.carsales.carsalestest.network.request.RequestCovidData
import com.carsales.carsalestest.network.response.ResponseCovidData
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

class CovidDataRepository(private val apiService : Api) {

    fun getCovidData(requestCovidData: String) : Observable<Response<ResponseCovidData>> {
        return apiService.getTotalCovid(ApiHeader.getHeader(), requestCovidData)
    }
}