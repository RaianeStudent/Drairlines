package com.example.drairlines.data.api

import com.example.drairlines.data.entity.AutenticacaoRequest
import com.example.drairlines.data.entity.AutenticacaoResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    @POST("usuario/criar")
    suspend fun criarUsuario(
        @Body autenticacaoRequest: AutenticacaoRequest
    ) : AutenticacaoResponse
}