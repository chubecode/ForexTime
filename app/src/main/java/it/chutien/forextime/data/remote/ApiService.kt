package it.chutien.forextime.data.remote

import io.reactivex.Single
import it.chutien.forextime.data.remote.response.ForexListResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by ChuTien on ${1/25/2017}.
 */
interface ApiService {
    @GET("/forexs")
    fun getForexList(@QueryMap hashMap: HashMap<String, String> = HashMap()): Single<ForexListResponse>
}

object ApiParams {
    const val PAGE = "page"
}