package com.example.covid19status.Di

import com.example.covid19status.Activity.MainActivity
import com.example.covid19status.Repository.CovidRepository
import com.example.covid19status.Retrofit.retrofitCovidBrazil
import com.example.covid19status.Retrofit.provideCovid19
import com.example.covid19status.Retrofit.retrofitCovidDate
import com.example.covid19status.Retrofit.retrofitDadoMundo
import com.example.covid19status.ViewModels.ViewModelCovid
import org.koin.dsl.module

object Modules {
    val viewModelCovidModule = module {
        factory { ViewModelCovid(get()) }
    }

    val activityModule = module {
        factory { MainActivity() }
    }

    val repositoryModule = module {
        factory { CovidRepository(get()) }
    }

    val netWorkModule = module(override = true) {

        factory { retrofitCovidBrazil() }
        factory { retrofitDadoMundo() }
        factory { retrofitCovidDate() }
        single { provideCovid19(get()) }

    }
}

