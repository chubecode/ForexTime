package it.chutien.forextime.data.remote

import io.reactivex.Single
import it.chutien.forextime.data.remote.response.ForexListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by ChuTien on ${1/25/2017}.
 */
interface ApiService {
    @GET("/latest")
    fun getForexList(@QueryMap queryMaps :  Map<String, String>): Single<ForexListResponse>
}

object ApiParams {
    const val ACCESS_KEY = "access_key"
    const val BASE = "base"
}