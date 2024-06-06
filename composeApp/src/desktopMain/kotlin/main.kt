import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import root.RootScreen

//@OptIn(ExperimentalDecomposeApi::class)
fun main(){
    //val lifecycle = LifecycleRegistry()

    application {
       /* val windowState = rememberWindowState()

        LifecycleController(lifecycle, windowState)*/
        Window(
            onCloseRequest = ::exitApplication,
            title = "ShopApp",
            state = WindowState(placement = WindowPlacement.Floating)
        ) {
            RootScreen()
        }
    }
}