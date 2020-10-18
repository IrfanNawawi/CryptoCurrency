package com.mockdroid.cryptocurrency.di

import com.mockdroid.cryptocurrency.BaseApp
import com.mockdroid.cryptocurrency.MainActivity
import com.mockdroid.cryptocurrency.ui.fragment.Home
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedModule::class])
interface SharedComponent {
    fun inject(application: BaseApp)
    fun inject(application: MainActivity)
    fun inject(fragment: Home)
}