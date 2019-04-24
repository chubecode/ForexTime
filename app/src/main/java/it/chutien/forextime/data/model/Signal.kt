package it.chutien.forextime.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by ChuTien on ${1/25/2017}.
 */

@Entity(tableName = "signals")
class Signal(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val forexFirst: String,
    val forexEnd: String,
    val type: String,
    val price: Double,
    val stopLoss: Double,
    val takeProfit1: Double,
    val takeProfit2: Double,
    val takeProfit3: Double,
    val publicDate: Long,
    val author: String
)