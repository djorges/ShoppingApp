import data.MainRepository
import data.ProductDto
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val mainRepository = MainRepository()

    private val _products = MutableStateFlow<List<ProductDto>>(emptyList())
    val products = _products.asStateFlow()

    init {
        viewModelScope.launch {
            mainRepository.getProducts().collect { products->
                _products.update { it + products }
            }
        }
    }
}