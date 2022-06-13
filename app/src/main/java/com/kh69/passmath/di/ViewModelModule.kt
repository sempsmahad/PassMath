package com.kh69.passmath.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kh69.passmath.MathViewModelFactory
import com.kh69.passmath.ui.dashboard.DashboardViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindUserViewModel(dashboardViewModel: DashboardViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(SearchViewModel::class)
//    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(RepoViewModel::class)
//    abstract fun bindRepoViewModel(repoViewModel: RepoViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MathViewModelFactory): ViewModelProvider.Factory
}
