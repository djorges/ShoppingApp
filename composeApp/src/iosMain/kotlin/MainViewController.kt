import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import root.DefaultRootComponent
import root.RootScreen

fun MainViewController() = ComposeUIViewController {
    val mainViewModel = MainViewModel()

    val root = DefaultRootComponent(
        componentContext = DefaultComponentContext(LifecycleRegistry()),
        mainViewModel = mainViewModel
    )

    RootScreen(component = root)
}