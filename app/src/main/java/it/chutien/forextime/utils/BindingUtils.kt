package it.chutien.forextime.utils

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Created by ChuTien on ${1/25/2017}.
 */

@BindingAdapter("onRefreshListener")
fun SwipeRefreshLayout.customRefreshListener(listener: SwipeRefreshLayout.OnRefreshListener?) {
    if (listener != null) setOnRefreshListener(listener)
}
@BindingAdapter("onNavigationItemSelectedListener")
fun BottomNavigationView.customSelectorListener(listener: BottomNavigationView.OnNavigationItemSelectedListener?){
    if (listener != null) {
        setOnNavigationItemSelectedListener(listener)
    }
}

@BindingAdapter("isRefreshing")
fun SwipeRefreshLayout.customRefreshing(refreshing: Boolean?) {
    isRefreshing = refreshing!!
}