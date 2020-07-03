package com.carsales.carsalestest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carsales.carsalestest.model.Resource
import com.carsales.carsalestest.network.request.RequestCovidData
import com.carsales.carsalestest.network.response.ResponseCovidData
import com.carsales.carsalestest.repository.CovidDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel (private val repository: CovidDataRepository) : ViewModel() {
    private val indicatorLiveData : MutableLiveData<Resource<ResponseCovidData>> = MutableLiveData()
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()


    fun getCovidData(date : String) : LiveData<Resource<ResponseCovidData>> {
        indicatorLiveData.postValue(Resource.loading(null))
        compositeDisposable.add(
            repository.getCovidData((date))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    indicatorLiveData.postValue(Resource.success(it.body()))
                },{
                    indicatorLiveData.postValue(
                        Resource.error("Ha ocurrido un error, intenta de nuevo", null))
                })
        )
        return indicatorLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun setData() : LiveData<Resource<ResponseCovidData>> {
        return indicatorLiveData
    }
}