import androidx.compose.ui.window.ComposeUIViewController
import di.initKoinIOS
import presentation.root.RootComponent
import presentation.root.RootScreen

val koin = initKoinIOS().koin

fun MainViewController() = ComposeUIViewController {
    val root = koin.get<RootComponent>()

    RootScreen(component = root)
}