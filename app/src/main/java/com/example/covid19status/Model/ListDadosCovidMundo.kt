package com.example.covid19status.Model

import com.google.gson.annotations.SerializedName

data class ListDadosCovidMundo (
    @SerializedName("data")
    var data: List<DadosCovidMundo>? = null
)