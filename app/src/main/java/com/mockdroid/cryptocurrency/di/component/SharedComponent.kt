package com.mockdroid.cryptocurrency.di.component

import com.mockdroid.cryptocurrency.BaseApp
import com.mockdroid.cryptocurrency.di.module.NetworkModule
import com.mockdroid.cryptocurrency.di.module.SharedModule
import com.mockdroid.cryptocurrency.ui.activity.MainActivity
import com.mockdroid.cryptocurrency.ui.fragment.Account
import com.mockdroid.cryptocurrency.ui.fragment.Balance
import com.mockdroid.cryptocurrency.ui.fragment.Home
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedModule::class, NetworkModule::class])
interface SharedComponent {
    fun inject(application: BaseApp)
    fun inject(application: MainActivity)
    fun inject(fragment: Home)
    fun inject(fragment: Account)
    fun inject(fragment: Balance)
}