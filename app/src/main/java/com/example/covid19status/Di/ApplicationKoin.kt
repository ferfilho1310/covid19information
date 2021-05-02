package com.example.covid19status.Di

import android.app.Application
import com.example.covid19status.Activity.activityModule
import com.example.covid19status.Repository.repositoryModule
import com.example.covid19status.Retrofit.netWorkModule
import com.example.covid19status.ViewModels.viewModelCovidModule
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
            modules(listOf(activityModule,netWorkModule,repositoryModule,viewModelCovidModule))
        }

    }
}