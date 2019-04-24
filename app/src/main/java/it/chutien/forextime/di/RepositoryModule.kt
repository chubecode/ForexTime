package it.chutien.forextime.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import it.chutien.forextime.data.local.db.AppDatabase
import it.chutien.forextime.data.repository.ForexRepository
import it.chutien.forextime.ui.list.ForexPriceViewModel
import it.chutien.forextime.data.repository.ForexRepositoryImpl
import it.chutien.forextime.utils.Constants
import org.koin.dsl.module.module

/**
 * Created by ChuTien on ${1/25/2017}.
 */
val repositoryModule = module(override = true) {
    single { createDatabaseName() }
    single { createAppDatabase(get(), get()) }
    single { createForexItemDao(get()) }
    single { createSignalDao(get()) }
    single { Gson() }
    single<ForexRepository> { ForexRepositoryImpl(get(), get()) }

}

fun createDatabaseName() = Constants.DATABASE_NAME

fun createAppDatabase(dbName: String, context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()

fun createForexItemDao(appDatabase: AppDatabase) = appDatabase.forexItemDao()

fun createSignalDao(appDatabase: AppDatabase) = appDatabase.signalDao()

