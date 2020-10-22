package com.mockdroid.cryptocurrency.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mockdroid.cryptocurrency.cryptomodel.CryptoModel
import com.mockdroid.cryptocurrency.service.CryptoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class StockViewModel:ViewModel() {
    private val mutableCryptoData = MutableLiveData<CryptoModel>()

    fun getMutableCryptoData():LiveData<CryptoModel>{
        loadData()
        return mutableCryptoData
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://min-api.cryptocompare.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val cryptoService:CryptoService = retrofit.create(CryptoService::class.java)
        val call:Call<CryptoModel> = cryptoService.getData()
        call.enqueue(object :Callback<CryptoModel> {
            override fun onResponse(call: Call<CryptoModel>, response: Response<CryptoModel>) {
                mutableCryptoData.value = response.body()
            }

            override fun onFailure(call: Call<CryptoModel>, t: Throwable) {
                Log.d("Crypto", t.message.toString())
            }
        })
    }
}