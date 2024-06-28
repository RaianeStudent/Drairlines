package com.example.drairlines.network.repository

import com.example.drairlines.data.TrechoDTOEstado
import com.example.drairlines.network.entity.AutenticacaoRequest
import com.example.drairlines.network.entity.AutenticacaoResponse
import com.example.drairlines.network.entity.ClienteDTO
import com.example.drairlines.network.entity.LoginRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {

    @POST("usuario/criar")
    suspend fun criarUsuario(@Body autenticacaoRequest: AutenticacaoRequest) : Response<AutenticacaoResponse>

    @POST("login/auth")
    suspend fun realizarLogin(@Body loginRequest : LoginRequest) : Response<AutenticacaoResponse>

    @GET("usuario/listar")
    suspend fun dadosUsuario() : Response<ClienteDTO>

    @GET("trecho/buscar")
    suspend fun buscarVoos(@Query("dataInicio") dataInicio : String) : Response<List<TrechoDTOEstado>>
}