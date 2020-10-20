package com.mockdroid.cryptocurrency.di.module

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.mockdroid.cryptocurrency.BaseApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedModule(private val application: BaseApp) {

    @Provides
    fun context(): Context {
        return application
    }

    @Provides
    @Singleton
    fun sharedPerf(context: Context): SharedPreferences {
        return context.getSharedPreferences("API", MODE_PRIVATE)
    }
}