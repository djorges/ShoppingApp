package list

import MainViewModel
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import data.ProductDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface ListComponent {
    val model:Value<Model>

    fun onItemClicked(item: ProductDto)

    data class Model(
        val items:List<ProductDto>
    )
}

class DefaultListComponent(
    private val componentContext: ComponentContext,
    private val mainViewModel: MainViewModel,
    private val onItemSelected:(item:ProductDto) -> Unit
): ListComponent, ComponentContext by componentContext{
    private val _model = MutableValue(ListComponent.Model(items = emptyList()))
    override val model: Value<ListComponent.Model> = _model

    override fun onItemClicked(item: ProductDto) {
        onItemSelected(item)
    }

    init {
        CoroutineScope(Dispatchers.Default).launch {
            mainViewModel.products.collect {
                _model.value = ListComponent.Model(it)
            }

        }
    }
}