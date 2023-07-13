package com.test.framework.mvvm.data.api

import com.test.framework.mvvm.data.model.Photo
import retrofit2.Response

interface ApiHelper {

    suspend fun getData(): Response<List<Photo>>
}