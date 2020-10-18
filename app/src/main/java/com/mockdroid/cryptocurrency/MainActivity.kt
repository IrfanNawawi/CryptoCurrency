package com.mockdroid.cryptocurrency

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mockdroid.cryptocurrency.ui.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as BaseApp)
            .getSharedComponent()
            .inject(this)

        btn_api.setOnClickListener {
            if (edt_api.text.isBlank()) {
                Snackbar.make(llMain, "Enter API Key", Snackbar.LENGTH_LONG).show()
            } else {
                val editor = sharedPreferences.edit()
                editor.putString("APIKEY", edt_api.text.toString())
                editor.apply()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}