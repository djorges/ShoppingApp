package data.db

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.example.shopapp.AppDatabase

actual class DriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema.synchronous(), "app.db")
    }
}