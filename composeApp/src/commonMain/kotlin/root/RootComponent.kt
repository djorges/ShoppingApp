package root

import MainViewModel
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import data.ProductDto
import detail.DefaultDetailComponent
import detail.DetailComponent
import kotlinx.serialization.Serializable
import list.DefaultListComponent
import list.ListComponent

interface RootComponent {

    val stack:Value<ChildStack<*,Child>>

    //Optional: pop multiple screens at a time on iOS
    fun onBackClicked()

    // Defines all possible child components
    sealed class Child{
        class ListChild(val component: ListComponent) : Child()

        class DetailChild(val component: DetailComponent) : Child()
    }
}

class DefaultRootComponent(
    private val componentContext: ComponentContext,
    private val mainViewModel: MainViewModel
): RootComponent, ComponentContext by componentContext {

    @Serializable
    private sealed interface Config {
        @Serializable
        data object List : Config

        @Serializable
        data class Details(val item: ProductDto) : Config
    }

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.List,
        handleBackButton = true,
        childFactory = ::child
    )
    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.List -> RootComponent.Child.ListChild(
                DefaultListComponent(componentContext,mainViewModel){ item ->
                    navigation.push(Config.Details(item))
                }
            )
            is Config.Details -> RootComponent.Child.DetailChild(
                DefaultDetailComponent(componentContext, config.item){
                    onBackClicked()
                }
            )
        }

    override fun onBackClicked() {
        navigation.pop()
    }

}