package com.carsales.carsalestest.view.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.carsales.carsalestest.R
import com.carsales.carsalestest.network.api.Api
import com.carsales.carsalestest.utils.NumberUtil
import com.carsales.carsalestest.utils.Status
import com.carsales.carsalestest.viewmodel.MainViewModel
import com.carsales.carsalestest.viewmodel.base.ViewModelFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProviders.of(this, ViewModelFactory(Api.create()))
            .get(MainViewModel::class.java)
        getData()
    }

    private fun getData(){
        val today = Calendar.getInstance()
        val month = NumberUtil.addCero(today.get(Calendar.MONTH))
        val day = NumberUtil.addCero(today.get(Calendar.DAY_OF_MONTH)-1)
        val date = "${today.get(Calendar.YEAR)}-$month-$day"
        viewmodel.getCovidData(date).observe( this, Observer {
            when(it.status){
                Status.LOADING -> { Log.e("LOAD", "-----------------") }
                Status.SUCCESS -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                Status.ERROR -> {
                    Log.e("ERRORRR?", " ${it.message}")}
            }
        })
    }

}