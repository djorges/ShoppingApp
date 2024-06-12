package presentation.list

import presentation.viewmodel.MainViewModel
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import data.dto.ProductDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

interface ListComponent {
    val model:Value<Model>

    fun onItemClicked(item: ProductDto)

    fun getSearchText(): StateFlow<String>

    fun onSearchTextChanged(text: String)

    data class Model(
        val items:List<ProductDto>
    )
}

class DefaultListComponent(
    private val componentContext: ComponentContext,
    private val mainViewModel: MainViewModel,
    private val onItemSelected:(item: ProductDto) -> Unit,
    private val onSearchChanged:(text: String) -> Unit
): ListComponent, ComponentContext by componentContext{
    private val _model = MutableValue(ListComponent.Model(items = emptyList()))
    override val model: Value<ListComponent.Model> = _model

    override fun onItemClicked(item: ProductDto) {
        onItemSelected(item)
    }

    override fun getSearchText(): StateFlow<String> = mainViewModel.searchQueryFlow

    override fun onSearchTextChanged(text: String) {
        onSearchChanged(text)
    }

    init {
        CoroutineScope(Dispatchers.Default).launch {
            mainViewModel.productsFlow.collect {
                _model.value = ListComponent.Model(it)
            }
        }
    }
}