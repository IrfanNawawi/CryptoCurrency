package com.mockdroid.cryptocurrency

import android.app.Application
import com.mockdroid.cryptocurrency.di.component.DaggerSharedComponent
import com.mockdroid.cryptocurrency.di.component.SharedComponent
import com.mockdroid.cryptocurrency.di.module.SharedModule

class BaseApp : Application() {

    lateinit var component: SharedComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerSharedComponent.builder()
            .sharedModule(SharedModule(this))
            .build()
    }

    fun getSharedComponent(): SharedComponent {
        return component
    }
}