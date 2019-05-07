@file:Suppress("UNREACHABLE_CODE")

package it.chutien.forextime.utils

import android.annotation.SuppressLint
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

/**
 * Created by ChuTien on ${1/25/2017}.
 */

@BindingAdapter("onRefreshListener")
fun SwipeRefreshLayout.customRefreshListener(listener: SwipeRefreshLayout.OnRefreshListener?) {
    if (listener != null) setOnRefreshListener(listener)
}

@BindingAdapter("onNavigationItemSelectedListener")
fun BottomNavigationView.customSelectorListener(listener: BottomNavigationView.OnNavigationItemSelectedListener?) {
    if (listener != null) {
        setOnNavigationItemSelectedListener(listener)
    }
}

@BindingAdapter("isRefreshing")
fun SwipeRefreshLayout.customRefreshing(refreshing: Boolean?) {
    isRefreshing = refreshing!!
}


object BindingUtils {
    private val SECOND_MILLIS = 1000
    private val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private val DAY_MILLIS = 24 * HOUR_MILLIS
    private val MONTH_MILLIS = 30 * DAY_MILLIS;
    @SuppressLint("SimpleDateFormat")
    @JvmStatic
    fun toTimeAgo(date: Date): String {
        val time = date.time

        val now = System.currentTimeMillis()
        if (time > now || time <= 0) {
            return ""
        }

        val diff = now - time
        return if (diff < MINUTE_MILLIS) {
            "just now"
        } else if (diff < 2 * MINUTE_MILLIS) {
            "a minute ago"
        } else if (diff < 50 * MINUTE_MILLIS) {
            "${diff / MINUTE_MILLIS} minutes ago"
        } else if (diff < 90 * MINUTE_MILLIS) {
            "an hour ago"
        } else if (diff < 24 * HOUR_MILLIS) {
            "${diff / HOUR_MILLIS} hours ago"
        } else if (diff < 48 * HOUR_MILLIS) {
            "yesterday"
        } else if (diff < 30 * DAY_MILLIS){
            "${diff / DAY_MILLIS} days ago"
        }else if (diff < 12 * MONTH_MILLIS){
            val months = diff / MONTH_MILLIS
            if (months<=1) "one month ago" else "${months} months ago"
        }else{
            val years = diff / (12*MONTH_MILLIS);
            if (years<=1) "one year ago" else "${years} years ago"
        }
    }

}
