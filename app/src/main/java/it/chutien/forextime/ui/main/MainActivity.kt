package it.chutien.forextime.ui.main

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import it.chutien.forextime.R
import it.chutien.forextime.databinding.ActivityMainBinding
import it.chutien.forextime.ui.base.BaseActivity
import it.chutien.forextime.ui.list.ForexPriceFragment
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {
    companion object {
        const val TAG = "MainActivity"
    }
    private lateinit var binding: ActivityMainBinding


    val viewModel : MainActivityViewModel  by viewModel()

    private var forexPriceFragment = ForexPriceFragment.newInstance("")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"MainActivity Start: onCreate")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)

        loadForexPriceFragment()

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"MainActivity Start: onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"MainActivity Start: onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"MainActivity Start: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"MainActivity End: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"MainActivity End: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"MainActivity End: onDestroy")
    }

    private fun loadForexPriceFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, forexPriceFragment, "ForexPriceFragment")
            .commit()
    }

}

