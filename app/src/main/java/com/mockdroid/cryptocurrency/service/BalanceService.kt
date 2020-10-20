package com.mockdroid.cryptocurrency.service

import com.mockdroid.cryptocurrency.model.Balance
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BalanceService {

    @GET("get_balance/")
    fun getBalance(@Query("api_key") api_key: String): Single<Balance>
}