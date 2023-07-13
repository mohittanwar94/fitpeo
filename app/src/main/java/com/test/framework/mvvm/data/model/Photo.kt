package com.test.framework.mvvm.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class Photo(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "albumId")
    val albumId: String = "",
    @Json(name = "title")
    val title: String = "",
    @Json(name = "url")
    val url: String = "",
    @Json(name = "thumbnailUrl")
    val thumbnailUrl: String = "",
) : Parcelable