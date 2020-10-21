package com.mockdroid.cryptocurrency.service

import com.mockdroid.cryptocurrency.model.Balance
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface BalanceService {

    @GET("get_balance/")
    fun getBalance(@Query("api_key") api_key: String): Single<Balance>

    @POST("withdraw/")
    @FormUrlEncoded
    fun withDrawCoin(
        @Field("api_key") api_key: String,
        @Field("amounts") amount: String,
        @Field("to_addresses") to_addresses: String
    ): Single<Balance>
}