package com.example.drairlines.data

sealed class HomeEvent {
    data class DataSelecionada(val data: String) : HomeEvent()
    object BotãoProcurarVooApertado : HomeEvent()
}
