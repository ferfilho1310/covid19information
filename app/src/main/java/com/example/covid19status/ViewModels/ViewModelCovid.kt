package com.example.covid19status.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid19status.Model.ListDadosCovidMundo
import com.example.covid19status.Repository.CovidRepository
import com.github.mikephil.charting.data.PieEntry

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelCovid(
    private val covidRespository: CovidRepository
) : ViewModel(){

    private val covidBrazil = MutableLiveData<List<PieEntry>>()
    private val covidMundo = MutableLiveData<List<PieEntry>>()
    val error = MutableLiveData<String>()

    val covidBr = covidBrazil
    val covidWorld = covidMundo
    val mortesCovidBrazil : ArrayList<PieEntry> = arrayListOf()
    val mortesCovidMundo : ArrayList<PieEntry> = arrayListOf()

    init {
        covidBrazil()
        covidMundo()
    }

     fun covidBrazil(){

        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                covidRespository.getDadosCovid()
            }.onSuccess{
                it.dadosCovidBrazilList
                    .filter { dadosCovidBrazil -> dadosCovidBrazil.deaths!!.toInt() > 6000 }
                    .forEach { dadosCovidBrazil ->
                        mortesCovidBrazil.add(PieEntry(dadosCovidBrazil.deaths!!.toFloat(),dadosCovidBrazil.state))
                        covidBr.postValue(mortesCovidBrazil)
                    }
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
                it.data!!
                    .filter {
                        dadosCovidMundo -> dadosCovidMundo.deaths!!.toInt() > 30000
                    }
                    .forEach {
                        dadosCovidMundo ->
                        mortesCovidMundo.add(PieEntry(dadosCovidMundo.deaths!!.toFloat(),dadosCovidMundo.country))
                        covidWorld.postValue(mortesCovidMundo)
                    }

            }.onFailure {
                error.postValue(it.message)
            }
        }
    }
}