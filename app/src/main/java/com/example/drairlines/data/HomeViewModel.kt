package com.example.drairlines.data

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.drairlines.MainActivity
import com.example.drairlines.navigation.CiaAereaAppRota
import com.example.drairlines.navigation.Tela
import com.example.drairlines.network.SessionManager
import com.example.drairlines.network.repository.RetrofitHelper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class HomeViewModel : ViewModel() {
    private lateinit var sessionManager: SessionManager
    private lateinit var retrofitHelper: RetrofitHelper
    val listaTrecho = mutableListOf<TrechoDTOEstado>()
    var homeUIEstado = mutableStateOf(TrechoUIEstado())

    fun onEvent(event: HomeEvent) {


        when (event) {

            is HomeEvent.DataSelecionada -> {
                homeUIEstado.value = homeUIEstado.value.copy(
                    data = event.data
                )
            }

            is HomeEvent.BotãoProcurarVooApertado -> {
                CiaAereaAppRota.navegateTo(Tela.TelaTrecho)
                sessionManager = SessionManager(MainActivity.appContext)
                sessionManager.getData()?.let {
                    buscarVoos(formatarData(it))
                }

            }
        }
    }

    private fun formatarData(data: String): String {
        val date: Date = SimpleDateFormat("dd/MM/yyyy").parse(data)

        val sdf: SimpleDateFormat
        sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        sdf.timeZone = TimeZone.getTimeZone("America/Sao_Paulo")
        val dateText = sdf.format(date)

        return dateText
    }

    fun buscarVoos(data: String): String {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                retrofitHelper = RetrofitHelper()

                sessionManager = SessionManager(MainActivity.appContext)
                var apiService = retrofitHelper.getApiService(MainActivity.appContext)
                val response = apiService.buscarVoos(data)

                if (response.isSuccessful) {
                    addLista(response.body().orEmpty())
                    CiaAereaAppRota.navegateTo(Tela.TelaTrecho)
                } else if (response.code() == 403) {

                    CiaAereaAppRota.navegateTo(Tela.TelaLogin)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        return getLista().toString()
    }

    fun addLista(lista: List<TrechoDTOEstado>) {
        for (trecho in lista) {
            listaTrecho.add(trecho)
        }

    }

    fun getLista() = listaTrecho


}
