package com.example.covid19status.Model

import com.google.gson.annotations.SerializedName

data class DadosCovidMundo(
    @SerializedName("country")
    var country: String? = null,

    @SerializedName("cases")
    var cases: Int? = null,

    @SerializedName("confirmed")
    var confirmed: Int? = null,

    @SerializedName("deaths")
    var deaths: Int? = null,

    @SerializedName("recovered")
    var recovered: Int? = null,

    @SerializedName("updated_at")
    var updatedAt: String? = null
)