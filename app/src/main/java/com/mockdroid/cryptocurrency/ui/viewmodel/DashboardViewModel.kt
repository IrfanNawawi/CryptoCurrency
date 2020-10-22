package com.mockdroid.cryptocurrency.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mockdroid.cryptocurrency.model.Balance
import com.mockdroid.cryptocurrency.repository.BalanceRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DashboardViewModel @Inject constructor(private val balanceRepository: BalanceRepository) :
    ViewModel() {

    private val disposable = CompositeDisposable()
    private val balanceMutableLiveData = MutableLiveData<Balance>()

    private val isLoading = MutableLiveData<Boolean>()
    private val isError = MutableLiveData<String>()

    fun getBalanceMutableliveData(apiKey: String): MutableLiveData<Balance> {
        loadData(apiKey)
        return balanceMutableLiveData
    }

    private fun loadData(apiKey: String) {
        isLoading.value = true
        disposable.add(
            balanceRepository.getBalanceRepo(apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    getBalanceMutableliveData(apiKey).value = data
                    isLoading.value = false
                }, { error ->
                    isLoading.value = false
                    Log.e("Dashboard", error.toString())
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