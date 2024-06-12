package di

import MainViewModel
import data.MainRepository
import db.datasource.ProductLocalDataSource
import db.datasource.ProductRemoteDataSource
import org.koin.dsl.module
import root.DefaultRootComponent
import root.RootComponent

fun commonModule() = cacheModule() + networkModule() + module{
    single {
        ProductRemoteDataSource(get())
    }
    single {
        ProductLocalDataSource(get(),get())
    }
    single{
        MainRepository(get(),get())
    }
    single {
        MainViewModel(get())
    }
    single<RootComponent> {
        DefaultRootComponent(
            componentContext = get(),
            mainViewModel = get()
        )
    }
}