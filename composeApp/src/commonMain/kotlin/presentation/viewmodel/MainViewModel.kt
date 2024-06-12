package presentation.viewmodel

import data.repository.MainRepository
import data.dto.ProductDto
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _searchQueryFlow = MutableStateFlow("")
    val searchQueryFlow = _searchQueryFlow.asStateFlow()

    val productsFlow = _searchQueryFlow
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isBlank()) {
                // If the query is blank, fetch all products
                mainRepository.getProducts()
            } else {
                // Perform a search
                mainRepository.getProducts().map {
                    it.filter { product ->
                        product.title!!.contains(query, ignoreCase = true)
                    }
                }
            }
        }

    fun searchProducts(query: String) {
        _searchQueryFlow.value = query
    }
}