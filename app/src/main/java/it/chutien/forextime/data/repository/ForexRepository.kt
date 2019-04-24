package it.chutien.forextime.data.repository

import io.reactivex.Single
import it.chutien.forextime.data.model.Forex.ForexItem
import it.chutien.forextime.data.remote.response.ForexListResponse

/**
 * Created by ChuTien on ${1/25/2017}.
 */
interface ForexRepository {

    fun getForexList(hashMap: HashMap<String, String>): Single<ForexListResponse>

    fun insertForexListInDb(list: List<ForexItem>)

    fun insertForextInDb(forex: ForexItem)


}