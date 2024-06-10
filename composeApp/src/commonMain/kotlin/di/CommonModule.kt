package di

import MainViewModel
import data.MainRepository
import org.koin.dsl.module
import root.DefaultRootComponent
import root.RootComponent

fun commonModule() = networkModule() + module{
    single{
        MainRepository(get())
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