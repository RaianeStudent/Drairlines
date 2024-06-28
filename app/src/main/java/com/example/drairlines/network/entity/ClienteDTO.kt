package com.example.drairlines.network.entity

import com.google.gson.annotations.SerializedName

data class ClienteDTO (

    @SerializedName("id")
    var clienteId : Long,

    @SerializedName("nome")
    var nomeCliente : String,

    @SerializedName("cpf")
    var cpf: String

)