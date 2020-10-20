package com.mockdroid.cryptocurrency.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mockdroid.cryptocurrency.di.ViewModelKey
import com.mockdroid.cryptocurrency.ui.viewmodel.HomeViewModel
import com.mockdroid.cryptocurrency.ui.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindViewModule(homeViewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}