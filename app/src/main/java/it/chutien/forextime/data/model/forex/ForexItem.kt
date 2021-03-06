package it.chutien.forextime.data.model.forex

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by ChuTien on ${1/25/2017}.
 */
@Entity(tableName = "forex_item")
class ForexItem (
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val price: Double,
    val time: Date,
    val isUpTrend: Boolean
)