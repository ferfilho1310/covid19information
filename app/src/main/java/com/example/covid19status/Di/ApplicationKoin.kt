package com.example.covid19status.Di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ApplicationKoin : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@ApplicationKoin)
            modules(
                listOf(
                    Modules.activityModule,
                    Modules.netWorkModule,
                    Modules.repositoryModule,
                    Modules.viewModelCovidModule
                )
            )
        }
    }
}