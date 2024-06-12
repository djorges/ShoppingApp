package org.example.shopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.defaultComponentContext
import db.DriverFactory
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import root.RootComponent
import root.RootScreen

class MainActivity : ComponentActivity() {

    private val rootComponent: RootComponent by inject()

    private val contextModule = module{
        single<ComponentContext> { defaultComponentContext() }
        single{ DriverFactory(applicationContext) }
    }

    init {
        loadKoinModules(contextModule)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RootScreen(component = rootComponent)
        }
    }
}