package com.test.framework.mvvm.data.api

import com.test.framework.mvvm.data.model.Photo
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getData(): Response<List<Photo>> = apiService.getJsonData()

}