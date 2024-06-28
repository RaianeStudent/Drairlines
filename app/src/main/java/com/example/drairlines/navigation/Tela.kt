package com.example.drairlines.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Tela {
    object TelaCadastro : Tela()
    object TelaLogin : Tela()
    object TelaHome : Tela()
    object TelaTrecho : Tela()
}

object CiaAereaAppRota {
    var telaAtual: MutableState<Tela> = mutableStateOf(Tela.TelaLogin)

    fun navegateTo(destination: Tela) {
        telaAtual.value = destination
    }

}