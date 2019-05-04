package it.chutien.forextime.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Created by ChuTien on ${1/25/2017}.
 */
abstract class BaseRefreshViewModel<Item>:BaseViewModel(){
    val isRefreshing = MutableLiveData<Boolean>().apply { value = false }

    val listItem = MutableLiveData<ArrayList<Item>>()

    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        if (isLoading.value == true || isRefreshing.value == true) return@OnRefreshListener
        isRefreshing.value = true
        loadData(-1)
    }
    fun onLoadSuccess() {
        isLoading.value = false
        isRefreshing.value = false
    }


    override fun onLoadFail(e: Throwable) {
        super.onLoadFail(e)
        isLoading.value = false
        isRefreshing.value = false
    }
}