package it.chutien.forextime.ui.main

import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomnavigation.BottomNavigationView
import it.chutien.forextime.R
import it.chutien.forextime.ui.base.BaseViewModel

/**
 * Created by ChuTien on ${1/25/2017}.
 */
class MainActivityViewModel  : BaseViewModel(){
    var dataStr = MutableLiveData<String>()
    override fun loadData(page: Int) {
        dataStr.value = page.toString()
    }

    val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {

        when (it.itemId) {
            R.id.navigation_all -> {
                loadData(-1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_buy -> {
                loadData(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_sell ->{
                loadData(0)
                return@OnNavigationItemSelectedListener true

            }
            else -> {
                loadData(-1)
                return@OnNavigationItemSelectedListener true
            }
        }
    }

}