package com.mockdroid.cryptocurrency.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mockdroid.cryptocurrency.model.Balance
import com.mockdroid.cryptocurrency.repository.TransactionRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class TransactionViewModel @Inject constructor(private val transactionRepository: TransactionRepository) :
    ViewModel() {

    private val disposable = CompositeDisposable()
    private val trasactionMutableLiveData = MutableLiveData<Balance>()
    private val isLoading = MutableLiveData<Boolean>()
    private val isError = MutableLiveData<String>()

    fun getTransactionMutableLiveData(api_key: String, type: String): MutableLiveData<Balance> {
        loadData(api_key, type)
        return trasactionMutableLiveData
    }

    private fun loadData(api_key: String, type: String) {
        isLoading.value = true
        disposable.add(
            transactionRepository.getTransactionRepository(api_key, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    getTransactionMutableLiveData(api_key, type).value = data
                    isLoading.value = false
                }, { error ->
                    isLoading.value = false
                    Log.d("Transaction", error.message.toString())
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