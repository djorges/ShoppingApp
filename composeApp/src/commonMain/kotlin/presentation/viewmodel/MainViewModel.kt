package presentation.viewmodel

import data.repository.MainRepository
import data.dto.ProductDto
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _searchQueryFlow = MutableStateFlow("")

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