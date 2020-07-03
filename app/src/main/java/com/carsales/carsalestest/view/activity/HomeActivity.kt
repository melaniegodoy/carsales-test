package com.carsales.carsalestest.view.activity


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.carsales.carsalestest.BR
import com.carsales.carsalestest.R
import com.carsales.carsalestest.network.api.Api
import com.carsales.carsalestest.utils.NumberUtil
import com.carsales.carsalestest.utils.Status
import com.carsales.carsalestest.viewmodel.MainViewModel
import com.carsales.carsalestest.viewmodel.base.ViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewmodel: MainViewModel
    private lateinit var binding : ViewDataBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        viewmodel = ViewModelProviders.of(this, ViewModelFactory(Api.create()))
            .get(MainViewModel::class.java)
        btnSelectDate.setOnClickListener(this)
        setData()
    }

    fun setData(){
        viewmodel.setData().observe(this, androidx.lifecycle.Observer {
            binding.setVariable(BR.data, it.data?.data)
        })
    }

    fun getCovidData(date : String){
        viewmodel.getCovidData(date).observe( this, androidx.lifecycle.Observer {
            when(it.status){
                Status.LOADING -> { Log.e("LOAD", "-----------------") }
                Status.SUCCESS -> {
                    binding.setVariable(BR.data, it.data?.data)
                }
                Status.ERROR -> {
                    Log.e("ERRORRR?", "${it.message}")}
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(view: View?) {
        when(view?.id){
            btnSelectDate.id -> {
                binding.setVariable(BR.show, true)
                datePicker.visibility = View.VISIBLE
                val today = Calendar.getInstance()
                datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)){
                        view, year, month, day ->
                    val month = NumberUtil.addCero(month +1)
                    val day = NumberUtil.addCero(day)
                    val date = "$year-$month-$day"
                    getCovidData(date)
                    binding.setVariable(BR.dateSelected, "Dia $day Mes $month  Año $year")
                    binding.setVariable(BR.show, false)
                }
            }
        }
    }

}