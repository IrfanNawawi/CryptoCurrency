package com.mockdroid.cryptocurrency.repository

import com.mockdroid.cryptocurrency.model.Balance
import com.mockdroid.cryptocurrency.service.BalanceService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val balanceService: BalanceService) {
    fun getTransactionRepository(api_key: String, type: String): Single<Balance> {
        return balanceService.getTransaction(api_key, type)
    }
}