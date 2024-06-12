package di

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import db.DriverFactory
import org.koin.dsl.module

val desktopModule = module {
    single { LifecycleRegistry() }
    single <ComponentContext>{ DefaultComponentContext(get<LifecycleRegistry>()) }
    single { DriverFactory() }
}

fun initKoinDesktop() = initKoin (additionalModules = listOf(desktopModule))
