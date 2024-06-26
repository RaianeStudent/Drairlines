package com.example.drairlines.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.drairlines.R
import com.example.drairlines.components.BotaoComponent
import com.example.drairlines.components.CampoDeTexto
import com.example.drairlines.components.SenhaCampoDeTexto
import com.example.drairlines.components.TextoClicavel
import com.example.drairlines.navigation.CiaAereaAppRota
import com.example.drairlines.navigation.Tela

@Composable
fun TelaLogin(){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(painter = painterResource(id = R.drawable.logonovo), contentDescription = "Logo Drairlines", modifier = Modifier.size(300.dp) )

            CampoDeTexto(valorLabel = stringResource(id = R.string.email), onTextSelect = {           })
            SenhaCampoDeTexto(valorLabel = stringResource(id = R.string.senha), PasswordVisualTransformation(), onTextSelect = {

            })
            BotaoComponent(value = stringResource(id = R.string.login), onBotaoApertado = {

            })
            TextoClicavel(value = stringResource(id = R.string.naoTemConta), textoSelecionado = {
                CiaAereaAppRota.navegateTo(Tela.TelaCadastro)
            })
        }
    }



}

@Preview
@Composable
fun DefaultPreviewOfTelaLogin(){
    TelaLogin()
}