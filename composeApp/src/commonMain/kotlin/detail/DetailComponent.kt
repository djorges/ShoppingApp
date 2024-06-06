package detail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import data.ProductDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface DetailComponent {
    val model: Value<Model>

    fun onBackPressed()

    data class Model(
        val item:ProductDto
    )
}
class DefaultDetailComponent(
    private val componentContext: ComponentContext,
    private val item:ProductDto,
    private val onBackPressed:() -> Unit
): DetailComponent, ComponentContext by componentContext{
    private val _model = MutableValue(DetailComponent.Model(item = item))
    override val model: Value<DetailComponent.Model> = _model

    override fun onBackPressed() {
        onBackPressed()
    }

    init {
        CoroutineScope(Dispatchers.Default).launch {
            //TODO:
        }
    }
}