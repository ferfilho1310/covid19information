package com.example.covid19status.Retrofit

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private var retrofit: Retrofit? = null
var URL = "https://covid19-brazil-api.now.sh/api/report/v1/"
var URL_DADOS_COVID_MUNDO = "https://covid19-brazil-api.now.sh/api/report/v1/countries/"
var URL_DADOS_COVID_DATA = "https://covid19-brazil-api.vercel.app/api/report/v1/brazil/"

fun retrofitCovidBrazil(): Retrofit? {
    retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit
}

fun retrofitDadoMundo(): Retrofit? {
    retrofit = Retrofit.Builder()
        .baseUrl(URL_DADOS_COVID_MUNDO)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit
}

fun retrofitCovidDate() : Retrofit? {
    retrofit = Retrofit.Builder()
        .baseUrl(URL_DADOS_COVID_DATA)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit
}

fun provideCovid19(retrofit: Retrofit) = retrofit.create(IProviderCovid::class.java)



