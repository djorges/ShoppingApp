package data.repository

import data.dto.ProductDto
import data.datasource.ProductLocalDataSource
import data.datasource.ProductRemoteDataSource
import kotlinx.coroutines.flow.flow
import org.example.shopapp.database.Product

class MainRepository(
    private val productLocalDataSource: ProductLocalDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource,
) {
    private suspend fun getAllProducts(forceReload: Boolean = false): List<ProductDto> {
        val cachedItems = productLocalDataSource.getAllProducts()
        return if(cachedItems.isNotEmpty() && !forceReload){
            // Return cached items
            cachedItems.map { it.toProductDto() }
        } else {
            // Fetch items from remote
            productLocalDataSource.deleteAllProducts()

            val remoteItems = productRemoteDataSource.getAllProducts()
            productLocalDataSource.insertAllProducts(remoteItems.map { it.toProduct() })

            remoteItems
        }
    }

    fun getProducts(forceReload: Boolean = false) = flow {
        emit(getAllProducts(forceReload))
    }
}

fun ProductDto.toProduct() = Product(
    id = id!!.toLong(),
    title = title.toString(),
    price = price ?: 0.0,
    description = description,
    category = category,
    image = image.toString()
)
fun Product.toProductDto() = ProductDto(
    id = id.toInt(),
    title = title,
    price = price,
    description = description,
    category = category,
    image = image
)
