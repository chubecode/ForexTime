package it.chutien.forextime.ui.list

import android.annotation.SuppressLint
import android.util.Log
import it.chutien.forextime.BuildConfig
import it.chutien.forextime.data.model.forex.ForexItem
import it.chutien.forextime.data.remote.ApiParams
import it.chutien.forextime.data.repository.ForexRepository
import it.chutien.forextime.ui.base.BaseRefreshViewModel

class ForexPriceViewModel constructor(
    private val forexRepository: ForexRepository
) : BaseRefreshViewModel<ForexItem>() {
    override fun loadData(page: Int) {
        val hashMap = HashMap<String, String>()
        hashMap[ApiParams.ACCESS_KEY] = BuildConfig.API_KEY
//        hashMap[ApiParams.BASE] = "USD"
        getForexList(hashMap)
    }

    @SuppressLint("CheckResult")
    private fun getForexList(hashMap: HashMap<String, String>) {
        forexRepository.getForexList(hashMap)
            .subscribe({
//                listItem.value = it.rates
//                forexRepository.insertForexListInDb(it.rates ?: listOf())
                val listValue = arrayListOf<ForexItem>()
                for (k in it.rates?.keySet()!!) {
                    listValue.add(ForexItem(it.base+"/"+k, it.rates.get(k).asDouble, it.timestamp!!.toLong(), false))
                }
                listItem.value = listValue
                forexRepository.insertForexListInDb(listValue)

                onLoadSuccess()
            }, {
                val listTest = arrayListOf<ForexItem>()
                var item = ForexItem("USD", 1.12, 9999, true)
                var item2 = ForexItem("GBP", 0.92, 1111, false)
                listTest.add(item)
                listTest.add(item2)
                listTest.add(item)
                listTest.add(item2)
                listTest.add(item2)
                listTest.add(item)
                listItem.value = listTest
                forexRepository.insertForexListInDb(listTest)
//                onLoadFail(it)
            })
    }

}
