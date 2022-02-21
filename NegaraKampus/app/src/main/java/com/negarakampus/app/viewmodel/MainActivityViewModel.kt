package com.negarakampus.app.viewmodel

import android.os.Handler
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.negarakampus.app.data.CountryModel
import com.negarakampus.app.network.RetroInstance
import com.negarakampus.app.network.RetroServiceInterface
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivityViewModel:ViewModel() {

    lateinit var liveDataList: MutableLiveData<List<CountryModel>>
    init{
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<CountryModel>>{
        return liveDataList
    }

    fun makeApiCall(){
        val retroInstance = RetroInstance.getRetroInstance()
      val retroService =   retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getcountryList()
        call.enqueue(object  : retrofit2.Callback<List<CountryModel>>{
            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
               liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                liveDataList.postValue(null)
            }


        })

    }


}