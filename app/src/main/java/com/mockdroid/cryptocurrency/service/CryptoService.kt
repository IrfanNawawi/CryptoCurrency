package com.mockdroid.cryptocurrency.service

import com.mockdroid.cryptocurrency.cryptomodel.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoService {
    @GET("/data/v2/histohour?fsym=BTC&tsym=USD&limit=10")
    fun getData(): Call<CryptoModel>
}