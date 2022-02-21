package com.negarakampus.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.negarakampus.app.data.KampusModel
import com.negarakampus.app.network.RetroInstanceKampus
import com.negarakampus.app.network.RetroServiceInterface
import retrofit2.Call
import retrofit2.Response

class DetailActivityViewModel:ViewModel() {

    lateinit var liveDataList: MutableLiveData<List<KampusModel>>
    init{
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<KampusModel>>{
        return liveDataList
    }

    fun makeApiCall(country: String) {
        val retroInstance = RetroInstanceKampus.getRetroInstanceKampus()
        val retroService =   retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getkampusList(country)
        println("asuu" + call)
        call.enqueue(object  : retrofit2.Callback<List<KampusModel>>{

            override fun onResponse(
                call: Call<List<KampusModel>>,
                response: Response<List<KampusModel>>
            ) {
                liveDataList.postValue(response.body())

            }

            override fun onFailure(call: Call<List<KampusModel>>, t: Throwable) {
                println("dadaf"+  t)
                liveDataList.postValue(null)
            }


        })

    }


}