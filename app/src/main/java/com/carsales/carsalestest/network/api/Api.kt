package com.carsales.carsalestest.network.api

import com.carsales.carsalestest.network.request.RequestCovidData
import com.carsales.carsalestest.network.response.ResponseCovidData
import com.carsales.carsalestest.utils.Constants.BASE_URL
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import kotlin.collections.HashMap

interface Api {

    @POST("?")
    fun getTotalCovid(
        @HeaderMap headers: HashMap<String,String>,
        @Query("date") date : String) : Observable<Response<ResponseCovidData>>


    companion object {
        private fun httpClient() : OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            return OkHttpClient.Builder().addInterceptor(interceptor).build()
        }

        fun create() : Api {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Api::class.java)
        }
    }

}