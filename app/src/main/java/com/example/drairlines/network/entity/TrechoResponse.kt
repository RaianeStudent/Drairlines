package com.example.drairlines.network.entity

import com.example.drairlines.data.AssentoDTOEstado
import com.google.gson.annotations.SerializedName

data class TrechoResponse(
    @SerializedName("id")
    var id: Long,

    @SerializedName("nome")
    var nome: String,

    @SerializedName("origem")
    var origem: String,

    @SerializedName("destino")
    var destino: String,

    @SerializedName("partida")
    var horaPartida: String,

    @SerializedName("chegada")
    var horaChegada: String,

    @SerializedName("valor")
    var valor: Float,

    @SerializedName("assentos")
    var listaAssentos: List<AssentoDTOResponse>
)