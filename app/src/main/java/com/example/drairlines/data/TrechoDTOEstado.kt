package com.example.drairlines.data

data class TrechoDTOEstado(
    var id: Long,
    var nome: String,
    var origem: String,
    var destino: String,
    var horaPartida: String,
    var horaChegada: String,
    var valor: Float,
    var listaAssentos: List<AssentoDTOEstado>
)
