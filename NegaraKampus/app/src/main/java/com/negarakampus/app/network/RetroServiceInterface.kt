package com.negarakampus.app.network

import android.text.AutoText
import com.negarakampus.app.data.CountryModel
import com.negarakampus.app.data.KampusModel
import okhttp3.Call
import retrofit2.http.*

interface RetroServiceInterface {

    @GET("all")
    fun getcountryList(): retrofit2.Call<List<CountryModel>>

    @GET("search")
    @Headers("Accept:  application/json")
    fun getkampusList(@Query("country") country:String?): retrofit2.Call<List<KampusModel>>



}