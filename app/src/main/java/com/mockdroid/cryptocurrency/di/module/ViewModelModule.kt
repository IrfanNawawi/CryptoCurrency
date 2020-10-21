package com.mockdroid.cryptocurrency.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mockdroid.cryptocurrency.di.ViewModelKey
import com.mockdroid.cryptocurrency.ui.viewmodel.AccountViewModel
import com.mockdroid.cryptocurrency.ui.viewmodel.HomeViewModel
import com.mockdroid.cryptocurrency.ui.viewmodel.TransactionViewModel
import com.mockdroid.cryptocurrency.ui.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindViewHomeModule(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindViewAmountModule(accountViewModel: AccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    abstract fun bindViewTransactionModule(transactionViewModel: TransactionViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}