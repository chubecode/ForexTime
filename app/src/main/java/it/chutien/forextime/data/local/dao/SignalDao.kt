package it.chutien.forextime.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Maybe
import it.chutien.forextime.data.model.Signal

/**
 * Created by ChuTien on ${1/25/2017}.
 */
@Dao
interface SignalDao {

    @Query("SELECT * FROM  signals ORDER BY id DESC")
    fun getSignalList(): Maybe<List<Signal>>

    @Query("SELECT * FROM signals WHERE (forexFirst=:forexFirst or '' =:forexFirst) AND (forexEnd=:forexEnd or '' =:forexEnd) AND (type =:type or ''=:type) AND (author =:author or '' =:author) ORDER BY id DESC LIMIT :number")
    fun getSignalsBy(
        forexFirst: String = "",
        forexEnd: String = "",
        type: String = "",
        number: Int = -1,
        author: String = ""
    ) : Maybe<List<Signal>>


}