package db.datasource

import app.cash.sqldelight.async.coroutines.awaitAsList
import db.DbHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.example.shopapp.database.Product

class ProductLocalDataSource(
    private val dbHelper: DbHelper,
    private val coroutineScope: CoroutineScope
) {
    suspend fun getAllProducts(): List<Product> {
        var items: List<Product>
        val result = coroutineScope.async {
            dbHelper.withDatabase {
                items = it.appDatabaseQueries.selectAllProducts().awaitAsList()

                return@withDatabase items
            }
        }
        return result.await()
    }

    suspend fun insertProduct(product: Product) {
        coroutineScope.launch {
            dbHelper.withDatabase {
                it.appDatabaseQueries.insertProduct(
                    product.id,
                    product.title,
                    product.image,
                    product.price,
                    product.category,
                    product.description
                )
            }
        }
    }

    suspend fun insertAllProducts(products: List<Product>){
        coroutineScope.launch {
            dbHelper.withDatabase { database ->
                products.forEach { product ->
                    database.appDatabaseQueries.insertProduct(
                        product.id,
                        product.title,
                        product.image,
                        product.price,
                        product.category,
                        product.description
                    )
                }
            }
        }
    }

    suspend fun deleteAllProducts() {
        coroutineScope.launch {
            dbHelper.withDatabase {
                it.appDatabaseQueries.removeAllProducts()
            }
        }
    }
}