package com.example.covid19status.ViewModels

import android.app.usage.UsageEvents
import android.service.autofill.FillEventHistory
import android.widget.Toast
import androidx.lifecycle.*
import com.example.covid19status.Model.ListDadosCovidBrazil
import com.example.covid19status.Model.ListDadosCovidMundo
import com.example.covid19status.Repository.CovidRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val viewModelCovidModule = module {
    factory { ViewModelCovid(get()) }
}

class ViewModelCovid(
    private val covidRespository : CovidRepository) : ViewModel(){

    private val covidBrazil = MutableLiveData<ListDadosCovidBrazil>()
    private val covidMundo = MutableLiveData<ListDadosCovidMundo>()
    val error = MutableLiveData<String>()

    val covidBr = covidBrazil
    val covidWorld = covidMundo

    init {
        covidBrazil()
        covidMundo()
    }

     fun covidBrazil(){
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                covidRespository.getDadosCovid()
            }.onSuccess{
                covidBr.postValue(it)
            }.onFailure {
                error.postValue(it.message)
            }
        }
    }

     fun covidMundo(){
         CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                covidRespository.getDadosCovidMundo()
            }.onSuccess {
                covidWorld.postValue(it)
            }.onFailure {
                error.postValue(it.message)
            }
        }
    }
}