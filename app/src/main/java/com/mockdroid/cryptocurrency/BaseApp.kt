package com.mockdroid.cryptocurrency

import android.app.Application
import com.mockdroid.cryptocurrency.di.DaggerSharedComponent
import com.mockdroid.cryptocurrency.di.SharedComponent
import com.mockdroid.cryptocurrency.di.SharedModule

class BaseApp : Application() {

    lateinit var component: SharedComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerSharedComponent.builder()
            .sharedModule(SharedModule(this))
            .build()
    }

    fun getSharedComponent(): SharedComponent{
        return component
    }
}