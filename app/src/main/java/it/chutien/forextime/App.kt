package it.chutien.forextime

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import it.chutien.forextime.di.networkModule
import it.chutien.forextime.di.repositoryModule
import it.chutien.forextime.di.viewModelModule
import org.koin.android.ext.android.startKoin

/**
 * Created by ChuTien on ${1/25/2017}.
 */
class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin(
            this, listOf(
                networkModule,
                repositoryModule,
                viewModelModule
            )
        )

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}