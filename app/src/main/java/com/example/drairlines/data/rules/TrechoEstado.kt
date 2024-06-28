package com.example.drairlines.data.rules

import com.example.drairlines.data.AssentoDTOEstado

data class TrechoEstado (
    var id: Long,
    var nome: String,
    var destino: String,
    var horaPartida: String,
    var horaChegada: String,
    var valor: Float,
    var listaAssentos: List<AssentoDTOEstado>
)