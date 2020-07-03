package com.carsales.carsalestest.network.api

import com.carsales.carsalestest.utils.Constants.KEY_VALUE

object ApiHeader {

    private var headerMap: HashMap<String, String> = HashMap()

    fun getHeader(): HashMap<String, String> {
        headerMap = HashMap()
        headerMap["X-RapidAPI-Key"] = KEY_VALUE
        return headerMap
    }
}