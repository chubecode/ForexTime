package it.chutien.forextime.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.chutien.forextime.R
import it.chutien.forextime.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
