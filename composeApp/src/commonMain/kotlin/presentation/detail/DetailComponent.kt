package presentation.detail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import data.dto.ProductDto

interface DetailComponent {
    val model: Value<Model>

    fun onBackPressed()

    data class Model(
        val item: ProductDto
    )
}
class DefaultDetailComponent(
    private val componentContext: ComponentContext,
    private val item: ProductDto,
    private val onBackPressedAction:() -> Unit
): DetailComponent, ComponentContext by componentContext{
    private val _model = MutableValue(DetailComponent.Model(item = item))
    override val model: Value<DetailComponent.Model> = _model

    override fun onBackPressed() {
        onBackPressedAction()
    }
}