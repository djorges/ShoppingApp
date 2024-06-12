package data.datasource

import data.dto.ProductDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProductRemoteDataSource(
    private val httpClient: HttpClient
) {
    suspend fun getAllProducts(): List<ProductDto> {
        val response = httpClient.get("https://fakestoreapi.com/products")
        return response.body()
    }
}
