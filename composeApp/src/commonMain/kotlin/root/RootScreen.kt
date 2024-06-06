package root

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import detail.DetailScreen
import list.ListScreen

@Composable
fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier
){
    MaterialTheme {
        Children(
            stack = component.stack,
            modifier = modifier,
            animation = stackAnimation(fade())
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.ListChild -> ListScreen(child.component)
                is RootComponent.Child.DetailChild -> DetailScreen(child.component)
            }
        }
    }
}