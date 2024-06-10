package di

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.koin.core.Koin
import org.koin.dsl.module
import root.RootComponent

val iosModule = module {
    single { LifecycleRegistry() }
    single <ComponentContext>{ DefaultComponentContext(get()) }
}

fun initKoinIOS() = di.initKoin (additionalModules = listOf(iosModule))

val Koin.rootComponent: RootComponent
    get() = get()

val Koin.lifecycleRegistry: LifecycleRegistry
    get() = get()