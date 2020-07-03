package com.carsales.carsalestest.viewmodel.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carsales.carsalestest.network.api.Api
import com.carsales.carsalestest.repository.CovidDataRepository
import com.carsales.carsalestest.viewmodel.MainViewModel

class ViewModelFactory(private val apiService: Api) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(CovidDataRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")

    }
}