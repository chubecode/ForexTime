package it.chutien.forextime.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by ChuTien on ${1/25/2017}.
 */
open class BaseListResponse<Item>(
    @SerializedName("page") val page: Int? = null,
    @SerializedName("total_results") val totalResults: Int? = null,
    @SerializedName("total_pages") val totalPages: Int? = null,
    @SerializedName("results") val results: ArrayList<Item>? = null
) : BaseResponse()