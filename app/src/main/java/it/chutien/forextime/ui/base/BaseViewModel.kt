package it.chutien.forextime.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import it.chutien.forextime.data.remote.BaseException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by ChuTien on ${1/25/2017}.
 */
abstract class BaseViewModel  : ViewModel(){

    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    val isRefreshing = MutableLiveData<Boolean>().apply { value = false }

    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        if (isLoading.value == true || isRefreshing.value == true) return@OnRefreshListener
        isRefreshing.value = true
        loadData(-1)
    }

    val errorMessage = MutableLiveData<String>()

    //rx
    val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    abstract fun loadData(page: Int)

    open fun onLoadSuccess(){
        isLoading.value = false
        isRefreshing.value = false
    }

    open fun onLoadFail(throwable: Throwable) {
        try {
            when (throwable) {
                is BaseException -> {
                    when (throwable.serverErrorCode) {
                        // custom server error code
                        else -> {
                            when (throwable.cause) {
                                is UnknownHostException -> {
                                    errorMessage.value = "No Internet Connection"
                                }
                                is SocketTimeoutException -> {
                                    errorMessage.value = "Connect timeout, please retry"
                                }
                                else -> {
                                    errorMessage.value = throwable.message
                                }
                            }
                        }
                    }
                }
                else -> {
                    errorMessage.value = throwable.message
                }
            }
        } catch (e: Exception) {
            errorMessage.value = throwable.message
        }
        isLoading.value = false
    }

    open fun showError(e: Throwable) {
        errorMessage.value = e.message
    }

    fun onActivityDestroyed() {
        compositeDisposable.clear()
    }
}