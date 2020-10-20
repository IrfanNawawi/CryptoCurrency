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
import com.mockdroid.cryptocurrency.databinding.FragmentHomeBinding
import com.mockdroid.cryptocurrency.ui.viewmodel.HomeViewModel
import javax.inject.Inject

class Home : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        (activity?.application as BaseApp)
            .getSharedComponent()
            .inject(this)

        fragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apikey = sharedPreferences.getString("APIKEY", "000000") ?: ""

        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        homeViewModel.isLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                fragmentHomeBinding.progresBar.visibility = View.VISIBLE
            } else {
                fragmentHomeBinding.progresBar.visibility = View.INVISIBLE
            }
        })

        homeViewModel.isError().observe(viewLifecycleOwner, Observer { error ->
            Log.e("Home", error.toString())
        })

        homeViewModel.getBalanceMutableliveData(apikey).observe(viewLifecycleOwner, Observer { data ->
            fragmentHomeBinding.balance = data
        })
    }
}