package com.fizhu.bikeappconcept.di

import androidx.room.Room
import com.fizhu.bikeappconcept.data.db.Db
import com.fizhu.bikeappconcept.data.db.LocalDataSource
import com.fizhu.bikeappconcept.data.pref.PrefDataSource
import com.fizhu.bikeappconcept.data.repository.AppRepository
import com.fizhu.bikeappconcept.data.repository.RemoteDataSource
import com.fizhu.bikeappconcept.data.repository.Repository
import com.fizhu.bikeappconcept.data.repository.RetrofitBuilder
import com.fizhu.bikeappconcept.viewmodels.CategoryHomeViewModel
import com.fizhu.bikeappconcept.viewmodels.LoginViewModel
import com.fizhu.bikeappconcept.viewmodels.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val remoteModule = module {
    single {
        RetrofitBuilder.apiService
    }
}

val remoteDataSource= module {
    single { RemoteDataSource(get()) }
}

val repositoryModule = module {
    single<Repository> { AppRepository(get(), get(),get()) }
}

val persistenceModule = module {
    single { PrefDataSource() }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), Db::class.java, "order_grubber")
            .fallbackToDestructiveMigration().build()
    }
    single { get<Db>().screenServerDao() }
}

val dataSourceModule = module {
    single { LocalDataSource(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { CategoryHomeViewModel(get()) }
}

val appModule =
    listOf(persistenceModule, databaseModule, dataSourceModule, repositoryModule, viewModelModule,remoteDataSource,remoteModule)