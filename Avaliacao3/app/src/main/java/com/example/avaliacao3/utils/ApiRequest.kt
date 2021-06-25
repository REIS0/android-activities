package com.example.avaliacao3.utils

import com.example.avaliacao3.model.WeatherList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    @GET("find")
    fun requestWeatherData(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") key: String
    ): Call<WeatherList>

}