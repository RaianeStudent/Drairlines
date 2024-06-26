package com.example.drairlines.data.datasource

import com.example.drairlines.data.api.ApiService
import com.example.drairlines.data.entity.AutenticacaoRequest
import com.example.drairlines.data.entity.AutenticacaoResponse
import javax.inject.Inject

class NewDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : NewDataSource {


    override suspend fun criarUsuario(autenticacaoRequest: AutenticacaoRequest): AutenticacaoResponse {
        return apiService.criarUsuario(autenticacaoRequest)
    }

}