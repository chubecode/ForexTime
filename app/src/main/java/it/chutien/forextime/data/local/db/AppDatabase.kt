package it.chutien.forextime.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import it.chutien.forextime.data.local.dao.ForexItemDao
import it.chutien.forextime.data.local.dao.SignalDao
import it.chutien.forextime.data.model.Forex.ForexItem
import it.chutien.forextime.data.model.Signal

/**
 * Created by ChuTien on ${1/25/2017}.
 */
@Database(entities = [ForexItem::class, Signal::class],version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun forexItemDao(): ForexItemDao

    abstract fun signalDao(): SignalDao
}