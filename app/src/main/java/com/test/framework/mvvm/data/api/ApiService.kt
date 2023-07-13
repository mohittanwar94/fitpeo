package com.test.framework.mvvm.data.api

import com.test.framework.mvvm.data.model.Photo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    suspend fun getJsonData(): Response<List<Photo>>
}