package com.example.drairlines.data

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.drairlines.MainActivity
import com.example.drairlines.data.rules.Validador
import com.example.drairlines.navigation.CiaAereaAppRota
import com.example.drairlines.navigation.Tela
import com.example.drairlines.network.SessionManager
import com.example.drairlines.network.entity.LoginRequest
import com.example.drairlines.network.repository.RetrofitHelper
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    @ActivityContext
    lateinit var context: Context

    private lateinit var sessionManager: SessionManager
    private lateinit var retrofitHelper : RetrofitHelper
    var loginUIEstado = mutableStateOf(CadastroUIEstado())

    var dadosLoginValidado = mutableStateOf(false)

    fun onEvent(event: LGEvent) {
        validarDados()
        when (event) {
            is LGEvent.EmailAlterado -> {
                loginUIEstado.value = loginUIEstado.value.copy(
                    email = event.email
                )

            }

            is LGEvent.SenhaAlterado -> {
                loginUIEstado.value = loginUIEstado.value.copy(
                    senha = event.senha
                )

            }

            is LGEvent.BotaoLoginApertado -> {
                    login(loginUIEstado.value.email, loginUIEstado.value.senha)

            }
        }
    }

    private fun validarDados() {

        val emailResultado = Validador.validarEmail(
            email = loginUIEstado.value.email
        )

        val senhaResultado = Validador.validarSenha(
            senha = loginUIEstado.value.senha
        )

        loginUIEstado.value = loginUIEstado.value.copy(
            emailError = emailResultado.status,
            senhaError = senhaResultado.status
        )

        if (emailResultado.status && senhaResultado.status ) {
            dadosLoginValidado.value= true
        } else {
            dadosLoginValidado.value=false
        }
    }

     fun login(email: String, senha: String) {
        System.out.println("PAASSSOU LOGIN")
        //viewModelScope.launch {

         CoroutineScope(Dispatchers.IO).launch{

             try {
                 retrofitHelper = RetrofitHelper()

                 sessionManager = SessionManager(MainActivity.appContext)
                 sessionManager.revokeAuthToken()
                 var apiService = retrofitHelper.getApiService(MainActivity.appContext)
                 val response = apiService.realizarLogin(LoginRequest(email, senha))

                 if (response.isSuccessful) {
                     response.body()?.let { sessionManager.saveAuthToken(it.token) }
                     CiaAereaAppRota.navegateTo(Tela.TelaHome)
                 } else if (response.code() == 403) {
                    loginUIEstado.value = loginUIEstado.value.copy(
                        senhaError = false
                    )

                 }

             } catch (e: Exception) {
                 sessionManager.revokeAuthToken()
                 e.printStackTrace()
             }
         }
        }
    //}

}