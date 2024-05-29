package data

import api.httpClient
import data.ProductDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow

class MainRepository {
    private suspend fun getProductsApi(): List<ProductDto> {
        val response = httpClient.get("https://fakestoreapi.com/products")
        return response.body()
    }

    fun getProducts() = flow {
        emit(getProductsApi())
    }
}