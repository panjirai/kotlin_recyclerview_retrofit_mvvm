package com.negarakampus.app.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstanceKampus {
    companion object{
        val BASE_URL ="http://universities.hipolabs.com/"//v2

        fun getRetroInstanceKampus(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}