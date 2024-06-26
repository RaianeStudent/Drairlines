package com.example.drairlines.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.drairlines.R
import com.example.drairlines.components.*
import com.example.drairlines.data.LoginViewModel
import com.example.drairlines.data.UIEvent
import com.example.drairlines.navigation.CiaAereaAppRota
import com.example.drairlines.navigation.Tela
import com.example.drairlines.ui.theme.CorTexto
import java.time.format.TextStyle

@Composable
fun TelaCadastro(loginViewModel: LoginViewModel = viewModel()) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){

        NormalTextoComponent(value = stringResource(id = R.string.ola))
        HeadingTextoComponent(value = stringResource(id = R.string.criarConta))
        Spacer(modifier = Modifier.height(20.dp))
        CampoDeTexto(valorLabel = stringResource(id = R.string.nome), onTextSelect = {
            loginViewModel.onEvent(UIEvent.NomeAlterado(it))
        }, errorStatus = loginViewModel.cadastroUIEstado.value.nomeError)

        CampoDeTexto(valorLabel = stringResource(id = R.string.email), onTextSelect = {
            loginViewModel.onEvent(UIEvent.EmailAlterado(it))
        }, errorStatus = loginViewModel.cadastroUIEstado.value.emailError)

        CampoDeTexto(valorLabel = stringResource(id = R.string.cpf), onTextSelect = {
            loginViewModel.onEvent(UIEvent.CpfAlterado(it))
        }, errorStatus = loginViewModel.cadastroUIEstado.value.cpfError)

        SenhaCampoDeTexto(valorLabel = stringResource(id = R.string.senha), PasswordVisualTransformation(), onTextSelect = {
            loginViewModel.onEvent(UIEvent.SenhaAlterado(it))
        }, errorStatus = loginViewModel.cadastroUIEstado.value.senhaError)

        Spacer(modifier = Modifier.height(40.dp))
        BotaoComponent(value = stringResource(id = R.string.cadastrar), onBotaoApertado = {
            loginViewModel.onEvent(UIEvent.BotaoCadastrarApertado)
        },
            isEnabled = loginViewModel.tudoValidado.value)
        TextoCadastroClicavel(value = stringResource(id = R.string.jaTemConta) ,textoSelecionado = {
            CiaAereaAppRota.navegateTo(Tela.TelaLogin)
        })
    }

    }
}

@Preview
@Composable
fun DefaultPreviewOfTelaCadastro() {
    TelaCadastro()
}