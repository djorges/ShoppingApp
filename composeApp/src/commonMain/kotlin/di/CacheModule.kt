package di

import db.DbHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

fun cacheModule() = module {
    single<CoroutineContext> { Dispatchers.IO }
    single { CoroutineScope(get()) }
    single{ DbHelper(get()) }
}