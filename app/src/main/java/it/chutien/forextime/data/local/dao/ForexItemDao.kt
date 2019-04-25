package it.chutien.forextime.data.local.dao

import androidx.room.*
import io.reactivex.Maybe
import it.chutien.forextime.data.model.forex.ForexItem

/**
 * Created by ChuTien on ${1/25/2017}.
 */
@Dao
interface ForexItemDao {

    @Query("SELECT * FROM forex_item")
    fun getListForexItem(): Maybe<List<ForexItem>>

    @Query("SELECT * FROM forex_item WHERE name = :name")
    fun getForexItem(name: String): Maybe<ForexItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forexItem: ForexItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forexItems: List<ForexItem>)

    @Delete
    fun delete(forexItem: ForexItem)

    @Query("DELETE FROM forex_item WHERE name = :name")
    fun delete(name: String)

    @Query("DELETE FROM forex_item")
    fun deleteAll()

}