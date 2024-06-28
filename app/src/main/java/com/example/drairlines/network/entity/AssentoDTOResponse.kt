package com.example.drairlines.network.entity

import com.google.gson.annotations.SerializedName

data class AssentoDTOResponse(
    @SerializedName("id")
    var id : Long,

    @SerializedName("nome")
    var nome: String,

    @SerializedName("assentoStatus")
    var status : String,

    @SerializedName("passageiro")
    var passageiro : Long,

    @SerializedName("valor")
    var valor: Long
)
