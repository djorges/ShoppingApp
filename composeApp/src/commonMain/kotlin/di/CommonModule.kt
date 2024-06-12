package di

import presentation.viewmodel.MainViewModel
import data.repository.MainRepository
import data.datasource.ProductLocalDataSource
import data.datasource.ProductRemoteDataSource
import org.koin.dsl.module
import presentation.root.DefaultRootComponent
import presentation.root.RootComponent

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