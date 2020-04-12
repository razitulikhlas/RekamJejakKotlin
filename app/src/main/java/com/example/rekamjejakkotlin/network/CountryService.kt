package com.example.rekamjejakkotlin.network

import com.example.rekamjejakkotlin.model.ResponseCountry
import retrofit2.Call
import retrofit2.http.GET

interface CountryService {
    @GET("indonesia/")
    fun getData() : Call<List<ResponseCountry>>
}