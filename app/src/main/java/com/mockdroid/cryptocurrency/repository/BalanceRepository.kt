package com.mockdroid.cryptocurrency.repository

import com.mockdroid.cryptocurrency.model.Balance
import com.mockdroid.cryptocurrency.service.BalanceService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class BalanceRepository @Inject constructor(private val balanceService: BalanceService) {

    fun getBalanceRepo(apiKey: String): Single<Balance> {
        return balanceService.getBalance(apiKey)
    }
}