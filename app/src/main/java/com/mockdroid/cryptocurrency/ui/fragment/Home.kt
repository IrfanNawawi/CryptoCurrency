package com.mockdroid.cryptocurrency.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mockdroid.cryptocurrency.BaseApp
import com.mockdroid.cryptocurrency.R
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class Home : Fragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        (activity!!.application as BaseApp)
            .getSharedComponent()
            .inject(this)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apikey = sharedPreferences.getString("APIKEY", "000000")

        txt.text = apikey
    }
}