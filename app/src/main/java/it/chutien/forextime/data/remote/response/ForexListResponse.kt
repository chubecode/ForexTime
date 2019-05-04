package it.chutien.forextime.data.remote.response

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

/**
 * Created by ChuTien on ${1/25/2017}.
 */
class ForexListResponse (
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("timestamp") val timestamp: Int? = null,
    @SerializedName("base") val base: String? = null,
    @SerializedName("date") val date: String? = null,
    @SerializedName("rates") val rates: JsonObject? = null
)