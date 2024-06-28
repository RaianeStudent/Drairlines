package com.example.drairlines.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.drairlines.MainActivity
import com.example.drairlines.data.rules.Validador
import com.example.drairlines.navigation.CiaAereaAppRota
import com.example.drairlines.navigation.Tela
import com.example.drairlines.network.SessionManager
import com.example.drairlines.network.entity.AutenticacaoRequest
import com.example.drairlines.network.repository.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CadastroViewModel : ViewModel() {
    private val TAG = CadastroViewModel::class.simpleName
    private lateinit var sessionManager: SessionManager
    private lateinit var retrofitHelper : RetrofitHelper
    var cadastroUIEstado = mutableStateOf(CadastroUIEstado())

    var tudoValidado = mutableStateOf(false)

    fun onEvent(event: UIEvent) {
        validarDados()
        when (event) {
            is UIEvent.NomeAlterado -> {
                cadastroUIEstado.value = cadastroUIEstado.value.copy(
                    nome = event.nome
                )

            }

            is UIEvent.EmailAlterado -> {
                cadastroUIEstado.value = cadastroUIEstado.value.copy(
                    email = event.email
                )

            }

            is UIEvent.CpfAlterado -> {
                cadastroUIEstado.value = cadastroUIEstado.value.copy(
                    cpf = event.cpf
                )

            }

            is UIEvent.SenhaAlterado -> {
                cadastroUIEstado.value = cadastroUIEstado.value.copy(
                    senha = event.senha
                )

            }

            is UIEvent.BotaoCadastrarApertado -> {
                cadastrar()
            }
        }
    }

    private fun cadastrar() {
        criandoUsuario(
            email = cadastroUIEstado.value.email,
            senha = cadastroUIEstado.value.senha,
            cpf = cadastroUIEstado.value.cpf,
            nome = cadastroUIEstado.value.nome
        )
    }

    private fun validarDados() {
        val nomeResultado = Validador.validarNome(
            nome = cadastroUIEstado.value.nome
        )

        val emailResultado = Validador.validarEmail(
            email = cadastroUIEstado.value.email
        )

        val cpfResultado = Validador.validarCpf(
            cpf = cadastroUIEstado.value.cpf
        )

        val senhaResultado = Validador.validarSenha(
            senha = cadastroUIEstado.value.senha
        )

        cadastroUIEstado.value = cadastroUIEstado.value.copy(
            nomeError = nomeResultado.status,
            emailError = emailResultado.status,
            cpfError = cpfResultado.status,
            senhaError = senhaResultado.status
        )

        if (nomeResultado.status && emailResultado.status && cpfResultado.status && senhaResultado.status ) {
            tudoValidado .value= true
        } else {
            tudoValidado.value=false
        }
    }

    private fun printState() {
        Log.d(TAG, "LOGAAAANDO")
        Log.d(TAG, cadastroUIEstado.value.toString())
    }

    fun criandoUsuario(email: String, senha: String, cpf: String, nome: String){
        CoroutineScope(Dispatchers.IO).launch{

            try {
                retrofitHelper = RetrofitHelper()

                sessionManager = SessionManager(MainActivity.appContext)
                sessionManager.revokeAuthToken()
                var apiService = retrofitHelper.getApiService(MainActivity.appContext)
                val response = apiService.criarUsuario(AutenticacaoRequest(nome, cpf, email, senha))

                if (response.isSuccessful) {
                    response.body()?.let { sessionManager.saveAuthToken(it.token) }
                    CiaAereaAppRota.navegateTo(Tela.TelaHome)
                }
            } catch (e: Exception) {
                sessionManager.revokeAuthToken()
                e.printStackTrace()
            }
        }
    }


}