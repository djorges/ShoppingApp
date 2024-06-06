package list

import AppContent
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun ListScreen(
    listComponent: ListComponent
){
    val products = listComponent.model.subscribeAsState()

    AppContent(products){
        listComponent.onItemClicked(it)
    }
}