package com.example.drairlines.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.drairlines.data.rules.Validador

class LoginViewModel : ViewModel() {
    private val TAG = LoginViewModel::class.simpleName

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


    }
}