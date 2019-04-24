package it.chutien.forextime.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson

/**
 * Created by ChuTien on ${1/25/2017}.
 */
class Converters {
    @TypeConverter
    fun listToJson(value: List<Any>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Any>? {

        val objects = Gson().fromJson(value, Array<Any>::class.java) as List<Any>
        return objects
    }
}