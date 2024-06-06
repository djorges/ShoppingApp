package org.example.shopapp

import MainViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import root.DefaultRootComponent
import root.RootScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = DefaultRootComponent(
            componentContext = defaultComponentContext(),
            mainViewModel = MainViewModel()
        )

        setContent {
            RootScreen(component = rootComponent)
        }
    }
}