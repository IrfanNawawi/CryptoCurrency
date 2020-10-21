package com.mockdroid.cryptocurrency.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mockdroid.cryptocurrency.BaseApp
import com.mockdroid.cryptocurrency.R
import com.mockdroid.cryptocurrency.databinding.FragmentBalanceBinding
import com.mockdroid.cryptocurrency.ui.viewmodel.TransactionViewModel
import javax.inject.Inject


class Balance : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var fragmentBalanceBinding: FragmentBalanceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as BaseApp)
            .getSharedComponent()
            .inject(this)
        fragmentBalanceBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_balance, container, false)
        // Inflate the layout for this fragment
        return fragmentBalanceBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiKey = sharedPreferences.getString("APIKEY", "000000") ?: ""

        transactionViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TransactionViewModel::class.java)

        transactionViewModel.isError().observe(viewLifecycleOwner, Observer { error ->
            fragmentBalanceBinding.errorTxt.text = error
        })

        transactionViewModel.isLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                fragmentBalanceBinding.progresBar.visibility = View.VISIBLE
            } else {
                fragmentBalanceBinding.progresBar.visibility = View.INVISIBLE
            }
        })

        transactionViewModel.getTransactionMutableLiveData(apiKey, "sent")
            .observe(viewLifecycleOwner, Observer { data ->
                fragmentBalanceBinding.balance = data
                Log.d("Transaction", data.toString())
            })
    }
}