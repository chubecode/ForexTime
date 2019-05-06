package it.chutien.forextime.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import it.chutien.forextime.BR
import it.chutien.forextime.R
import it.chutien.forextime.databinding.ActivityMainBinding
import it.chutien.forextime.ui.base.BaseActivity
import it.chutien.forextime.ui.list.ForexPriceFragment
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding,MainActivityViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    companion object {
        const val TAG = "MainActivity"
    }



    override val viewModel: MainActivityViewModel  by viewModel()

    private var forexPriceFragment = ForexPriceFragment.newInstance("")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MainActivity Start: onCreate")
        bindView(R.layout.activity_main)

        setSupportActionBar(binding.toolbar)

        loadForexPriceFragment()

        viewModel.apply {

            dataStr.observe(this@MainActivity, Observer {
                Toast.makeText(this@MainActivity, "You choose ${dataStr.value}", Toast.LENGTH_SHORT).show()
            })
            loadData(1)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity Start: onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "MainActivity Start: onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity Start: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MainActivity End: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity End: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity End: onDestroy")
    }

    private fun loadForexPriceFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, forexPriceFragment, "ForexPriceFragment")
            .commit()
    }

}

