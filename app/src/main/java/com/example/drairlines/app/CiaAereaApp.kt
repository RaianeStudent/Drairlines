package com.example.drairlines.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.drairlines.navigation.CiaAereaAppRota
import com.example.drairlines.navigation.Tela
import com.example.drairlines.pages.TelaCadastro
import com.example.drairlines.pages.TelaHome
import com.example.drairlines.pages.TelaLogin
import com.example.drairlines.pages.TelaTrechos

@Composable
fun CiaAereaApp(){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = CiaAereaAppRota.telaAtual, label = "") { estadoAtual->
            when(estadoAtual.value){
                is Tela.TelaCadastro ->{
                    TelaCadastro()
                }
                is Tela.TelaLogin ->{
                    TelaLogin()
                }
                is Tela.TelaHome ->{
                    TelaHome()
                }
                is Tela.TelaTrecho -> {
                    TelaTrechos()
                }
            }

        }

    }
}