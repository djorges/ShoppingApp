import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import di.initKoinDesktop
import root.RootComponent
import root.RootScreen

val koin = initKoinDesktop().koin

fun main(){
    application {
        val windowState = rememberWindowState()
        val rootComponent = koin.get<RootComponent>()

        Window(
            onCloseRequest = ::exitApplication,
            title = "ShopApp",
            state = windowState
        ) {
            RootScreen(rootComponent)
        }
    }
}