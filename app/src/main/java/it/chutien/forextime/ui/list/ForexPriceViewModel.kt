package it.chutien.forextime.ui.list

import android.annotation.SuppressLint
import it.chutien.forextime.data.model.forex.ForexItem
import it.chutien.forextime.data.remote.ApiParams
import it.chutien.forextime.data.repository.ForexRepository
import it.chutien.forextime.ui.base.BaseRefreshViewModel

class ForexPriceViewModel constructor(
    private val forexRepository: ForexRepository
) : BaseRefreshViewModel<ForexItem>() {
    override fun loadData(page: Int) {
        val hashMap = HashMap<String, String>()
        hashMap[ApiParams.PAGE] = page.toString()
        getForexList(hashMap)
    }

    @SuppressLint("CheckResult")
    private fun getForexList(hashMap: HashMap<String, String>) {
        forexRepository.getForexList(hashMap)
            .subscribe({
                listItem.value = it.results
                forexRepository.insertForexListInDb(it.results ?: listOf())
                onLoadSuccess()
            }, {
                val listTest = arrayListOf<ForexItem>()
                var item = ForexItem("USD",1.12,9999,true)
                var item2 = ForexItem("GBP",0.92,1111,false)
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
