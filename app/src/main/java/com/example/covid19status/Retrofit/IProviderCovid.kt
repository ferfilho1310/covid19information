package com.example.covid19status.Retrofit

import com.example.covid19status.Model.ListDadosCovidBrazil
import com.example.covid19status.Model.ListDadosCovidMundo
import retrofit2.http.GET
import retrofit2.http.Url

interface IProviderCovid {

    @GET
    suspend fun getDadosCovid(@Url url: String?): ListDadosCovidBrazil

    @GET
    suspend fun getDadosCovidMundo(@Url url: String?): ListDadosCovidMundo
}