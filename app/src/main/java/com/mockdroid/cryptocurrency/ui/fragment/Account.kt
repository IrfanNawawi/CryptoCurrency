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
import com.mockdroid.cryptocurrency.databinding.FragmentAccountBinding
import com.mockdroid.cryptocurrency.ui.viewmodel.AccountViewModel
import javax.inject.Inject

class Account : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var accountViewModel: AccountViewModel
    private lateinit var fragmentAccountBinding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as BaseApp).getSharedComponent().inject(this)
        fragmentAccountBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        // Inflate the layout for this fragment
        return fragmentAccountBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiKey = sharedPreferences.getString("APIKEY", "000000") ?: ""

        accountViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AccountViewModel::class.java)

        accountViewModel.isError().observe(viewLifecycleOwner, Observer { error ->
            fragmentAccountBinding.errorTxt.text = error
        })

        accountViewModel.isLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                fragmentAccountBinding.progressBar.visibility = View.VISIBLE
            } else {
                fragmentAccountBinding.progressBar.visibility = View.INVISIBLE
            }
        })

        accountViewModel.getWithDrawCoinRepoLiveData(apiKey, "100", "3NkGVwyGMVD7ZxZspELiT6iVvDb28vbENw")
            .observe(viewLifecycleOwner, Observer { data ->
                Log.e("Account", data.status)
            })
    }
}