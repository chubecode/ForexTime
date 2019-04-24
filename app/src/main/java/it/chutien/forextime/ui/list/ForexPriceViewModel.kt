package it.chutien.forextime.ui.list

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel;
import it.chutien.forextime.data.remote.ApiParams
import it.chutien.forextime.data.repository.ForexRepository
import it.chutien.forextime.ui.base.BaseViewModel

class ForexPriceViewModel constructor(
    val forexRepository: ForexRepository
) : BaseViewModel() {
    override fun loadData(page: Int) {
        val hashMap = HashMap<String, String>()
        hashMap.put(ApiParams.PAGE, page.toString())
        getForexList(hashMap)
    }

    @SuppressLint("CheckResult")
    private fun getForexList(hashMap: HashMap<String, String>) {
        forexRepository.getForexList(hashMap)
            .subscribe({
                forexRepository.insertForexListInDb(it.results ?: listOf())
                onLoadSuccess()
            }, {
                onLoadFail(it)
            })
    }

}
