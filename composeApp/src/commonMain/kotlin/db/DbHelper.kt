package db

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import org.example.shopapp.AppDatabase

class DbHelper(private val driverFactory: DriverFactory) {
    private var database: AppDatabase? = null

    private val mutex = Mutex()

    suspend fun <Result:Any> withDatabase(
        block: suspend (AppDatabase) -> Result
    ): Result = mutex.withLock {
        if(database == null){
            database = getDatabase(driverFactory)
        }
        return@withLock block(database!!)
    }

    private suspend fun getDatabase(driverFactory: DriverFactory):AppDatabase {
        return AppDatabase(driver = driverFactory.createDriver())
    }
}