package com.example.covid19status.Model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ListDadosCovidBrazil (
    @SerializedName("data")
    var dadosCovidBrazilList: List<DadosCovidBrazil> = ArrayList()
)