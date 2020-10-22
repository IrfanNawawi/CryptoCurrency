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
import com.mockdroid.cryptocurrency.databinding.FragmentDashboardBinding
import com.mockdroid.cryptocurrency.ui.viewmodel.DashboardViewModel
import javax.inject.Inject

class Dashboard : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var fragmentDashboardBinding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        (activity?.application as BaseApp).getSharedComponent().inject(this)

        fragmentDashboardBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)

        return fragmentDashboardBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apikey = sharedPreferences.getString("APIKEY", "000000") ?: ""

        dashboardViewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)

        dashboardViewModel.isLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                fragmentDashboardBinding.progresBar.visibility = View.VISIBLE
            } else {
                fragmentDashboardBinding.progresBar.visibility = View.INVISIBLE
            }
        })

        dashboardViewModel.isError().observe(viewLifecycleOwner, Observer { error ->
            Log.e("Home", error.toString())
        })

        dashboardViewModel.getBalanceMutableliveData(apikey).observe(viewLifecycleOwner, Observer { data ->
            fragmentDashboardBinding.balance = data
        })


    }
}