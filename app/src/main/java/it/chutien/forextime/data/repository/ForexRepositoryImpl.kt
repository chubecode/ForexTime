package it.chutien.forextime.data.repository

import android.util.Log
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import it.chutien.forextime.data.local.dao.ForexItemDao
import it.chutien.forextime.data.model.forex.ForexItem
import it.chutien.forextime.data.remote.ApiService
import it.chutien.forextime.data.remote.response.ForexListResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

/**
 * Created by ChuTien on ${1/25/2017}.
 */
class ForexRepositoryImpl constructor(
    val apiService: ApiService,
    val forexItemDao: ForexItemDao
) : ForexRepository{

    override fun getForexList(hashMap: HashMap<String, String>): Single<ForexListResponse> {
        return apiService.getForexList(hashMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertForexListInDb(list: List<ForexItem>) {
        GlobalScope.async {
            try {
                forexItemDao.insert(list)
            } catch (e: Throwable) {
                Log.e("ForexRepository", e.toString())
            }
        }
    }

    override fun insertForextInDb(forex: ForexItem) {
        GlobalScope.async {
            try {
                forexItemDao.insert(forex)
            } catch (e: Throwable) {
                Log.e("ForexRepository", e.toString())
            }
        }
    }
}
