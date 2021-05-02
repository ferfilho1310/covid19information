package com.example.covid19status.Model

import com.google.gson.annotations.SerializedName

data class DadosCovidBrazil(
    @SerializedName("uid")
    var uid: Int? = null,

    @SerializedName("uf")
    var uf: String? = null,

    @SerializedName("state")
    var state: String? = null,

    @SerializedName("cases")
    var cases: Int? = null,

    @SerializedName("deaths")
    var deaths: Int? = null,

    @SerializedName("suspects")
    var suspects: Int? = null,

    @SerializedName("refuses")
    var refuses: Int? = null,

    @SerializedName("datetime")
    var datetime: String? = null
)