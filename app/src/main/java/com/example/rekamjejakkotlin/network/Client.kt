package com.example.rekamjejakkotlin.network

import com.example.rekamjejakkotlin.utils.Common
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    companion object{
        fun setInit() : Retrofit{
            return Retrofit.Builder()
                .baseUrl(Common.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getInstance() : CountryService{
            return  setInit().create(CountryService::class.java)
        }
    }

}