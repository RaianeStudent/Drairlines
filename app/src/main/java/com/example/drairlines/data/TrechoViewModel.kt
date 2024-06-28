package com.example.drairlines.data

import androidx.lifecycle.ViewModel
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

class TrechoViewModel : ViewModel() {
    private lateinit var sessionManager: SessionManager
    private lateinit var retrofitHelper: RetrofitHelper
    val listaTrecho = mutableListOf<TrechoDTOEstado>()

    fun init() : String {
        var response = ""
        sessionManager = SessionManager(MainActivity.appContext)
        sessionManager.getData()?.let {
            response = buscarVoos(formatarData(it))
        }

        return response
    }



    private fun formatarData(data: String): String {
        val date: Date = SimpleDateFormat("dd/MM/yyyy").parse(data)

        val sdf: SimpleDateFormat
        sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        sdf.timeZone = TimeZone.getTimeZone("America/Sao_Paulo")
        val dateText = sdf.format(date)

        return dateText
    }

    fun buscarVoos(data: String) : String {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                retrofitHelper = RetrofitHelper()

                sessionManager = SessionManager(MainActivity.appContext)
                var apiService = retrofitHelper.getApiService(MainActivity.appContext)
                val response = apiService.buscarVoos(data)

                if (response.isSuccessful) {
                    addLista(response.body().orEmpty())
                } else if (response.code() == 403) {
                    CiaAereaAppRota.navegateTo(Tela.TelaLogin)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        return getLista().toString()
    }

    fun addLista(lista : List<TrechoDTOEstado>) {
        for (trecho in lista) {
            listaTrecho.add(trecho)
        }

    }

    fun getLista() = listaTrecho



}