package it.chutien.forextime.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import it.chutien.forextime.R
import it.chutien.forextime.databinding.ActivityMainBinding
import it.chutien.forextime.ui.base.BaseActivity
import it.chutien.forextime.ui.list.ForexPriceFragment
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding


    val viewModel by viewModel<MainActivityViewModel>()

    var forexPriceFragment = ForexPriceFragment.newInstance("")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)

        loadForexPriceFragment()

    }

    private fun loadForexPriceFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, forexPriceFragment, "ForexPriceFragment")
            .commit()
    }

}

