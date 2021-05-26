package com.example.covid19status.Repository

import com.example.covid19status.Retrofit.IProviderCovid
import com.example.covid19status.Retrofit.URL
import com.example.covid19status.Retrofit.URL_DADOS_COVID_MUNDO

class CovidRepository(private val IProviderCovid : IProviderCovid) {
    suspend fun getDadosCovid() = IProviderCovid.getDadosCovid(URL)
    suspend fun getDadosCovidMundo() = IProviderCovid.getDadosCovidMundo(URL_DADOS_COVID_MUNDO)
    suspend fun getDadoCovidPorData(id: String?) = IProviderCovid.getDadosCovidPorData(id!!)
}