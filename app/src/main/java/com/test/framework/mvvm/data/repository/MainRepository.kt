package com.test.framework.mvvm.data.repository

import com.test.framework.mvvm.data.api.ApiHelper

class MainRepository (private val apiHelper: ApiHelper) {

    suspend fun getData() =  apiHelper.getData()

}