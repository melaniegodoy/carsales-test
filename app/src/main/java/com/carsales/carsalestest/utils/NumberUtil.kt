package com.carsales.carsalestest.utils

object NumberUtil {

    fun addCero(number : Int) : String{
        var numberWithZero = ""
        if(number < 10){
            numberWithZero = "0$number"
        } else {
            numberWithZero = "$number"
        }
        return numberWithZero
    }

}