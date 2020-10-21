package com.mockdroid.cryptocurrency.repository

import com.mockdroid.cryptocurrency.model.Balance
import com.mockdroid.cryptocurrency.service.BalanceService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WithdrawCoinRepository @Inject constructor(private val balanceService: BalanceService) {
    fun getWithdrawCoinRepo(api_key: String, amount: String, to_address: String): Single<Balance> {
        return balanceService.withDrawCoin(api_key, amount, to_address)
    }
}