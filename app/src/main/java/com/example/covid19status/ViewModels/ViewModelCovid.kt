package com.example.covid19status.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid19status.Model.DadosCovidBrazil
import com.example.covid19status.Model.ListDadosCovidBrazil
import com.example.covid19status.Repository.CovidRepository
import com.github.mikephil.charting.data.PieEntry

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ViewModelCovid(
    private val covidRespository: CovidRepository
) : ViewModel() {

    private val covidBrazil = MutableLiveData<List<PieEntry>>()
    private val covidMundo = MutableLiveData<List<PieEntry>>()
    private val lsCovidPorData = MutableLiveData<List<PieEntry>>()
    val error = MutableLiveData<String>()

    val covidBr = covidBrazil
    val covidWorld = covidMundo
    val covidMortePorData = lsCovidPorData

    val mortesCovidBrazil: ArrayList<PieEntry> = arrayListOf()
    val mortesCovidMundo: ArrayList<PieEntry> = arrayListOf()
    val mortesCovidBrazilPorData: ArrayList<PieEntry> = arrayListOf()

    init {
        covidBrazil()
        covidMundo()
    }

    fun covidBrazil() {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                covidRespository.getDadosCovid()
            }.onSuccess {
                it.dadosCovidBrazilList
                    .filter { dadosCovidBrazil -> dadosCovidBrazil.deaths!!.toInt() > 6000 }
                    .forEach { dadosCovidBrazil ->
                        mortesCovidBrazil.add(
                            PieEntry(
                                dadosCovidBrazil.deaths!!.toFloat(),
                                dadosCovidBrazil.state
                            )
                        )
                        covidBr.postValue(mortesCovidBrazil)
                    }
            }.onFailure {
                error.postValue(it.message)
            }
        }
    }

    fun covidMundo() {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                covidRespository.getDadosCovidMundo()
            }.onSuccess {
                it.data!!
                    .filter { dadosCovidMundo ->
                        dadosCovidMundo.deaths!!.toInt() > 30000
                    }
                    .forEach { dadosCovidMundo ->
                        mortesCovidMundo.add(
                            PieEntry(
                                dadosCovidMundo.deaths!!.toFloat(),
                                dadosCovidMundo.country
                            )
                        )
                        covidWorld.postValue(mortesCovidMundo)
                    }
            }.onFailure {
                error.postValue(it.message)
            }
        }
    }


    fun covidBrazilPorData(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                covidRespository.getDadoCovidPorData(id)
            }.onSuccess {
                mortesCovidBrazilPorData.clear()
                it.dadosCovidBrazilList
                    .filter { dadosCovidBrazil -> dadosCovidBrazil.deaths!!.toInt() > 6000 }
                    .forEach { dados ->
                    mortesCovidBrazilPorData.add(
                        PieEntry(
                            dados.deaths!!.toFloat(),
                            dados.state
                        )
                    )
                }
                covidMortePorData.postValue(mortesCovidBrazilPorData)
            }.onFailure {
                error.postValue(it.message)
            }
        }
    }
}