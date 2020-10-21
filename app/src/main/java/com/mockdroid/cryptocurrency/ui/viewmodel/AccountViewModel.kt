package com.mockdroid.cryptocurrency.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mockdroid.cryptocurrency.model.Balance
import com.mockdroid.cryptocurrency.repository.WithdrawCoinRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val withDrawCoinRepository: WithdrawCoinRepository) :
    ViewModel() {
    private val disposable = CompositeDisposable()
    private val withDrawCoinRepoLiveData = MutableLiveData<Balance>()

    private val isLoading = MutableLiveData<Boolean>()
    private val isError = MutableLiveData<String>()

    fun getWithDrawCoinRepoLiveData(
        api_key: String,
        amount: String,
        to_address: String
    ): MutableLiveData<Balance> {
        loadData(api_key, amount, to_address)
        return withDrawCoinRepoLiveData
    }

    private fun loadData(api_key: String, amount: String, to_address: String) {
        isLoading.value = true
        disposable.add(
            withDrawCoinRepository.getWithdrawCoinRepo(api_key, amount, to_address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    isLoading.value = false
                    getWithDrawCoinRepoLiveData(api_key, amount, to_address).value = data
                }, { error ->
                    isLoading.value = false
                    isError.value = error.toString()
                })
        )
    }

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun isError(): LiveData<String> {
        return isError
    }
}