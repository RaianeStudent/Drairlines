package com.example.drairlines.network.entity

import com.google.gson.annotations.SerializedName

data class AutenticacaoRequest(

    @SerializedName("nome")
    var nome:String,

    @SerializedName("cpf")
    var cpf:String,

    @SerializedName("email")
    var email: String,

    @SerializedName("senha")
    var senha:String
)