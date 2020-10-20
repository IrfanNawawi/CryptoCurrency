package com.mockdroid.cryptocurrency.di.module

import com.mockdroid.cryptocurrency.service.BalanceService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://block.io/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    internal fun provideBalanceService(retrofit: Retrofit): BalanceService {
        return retrofit.create(BalanceService::class.java)
    }
}