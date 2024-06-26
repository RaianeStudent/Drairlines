package com.example.drairlines.data.datasource

import com.example.drairlines.data.entity.AutenticacaoRequest
import com.example.drairlines.data.entity.AutenticacaoResponse

interface NewDataSource {

    suspend fun criarUsuario( autenticacaoRequest: AutenticacaoRequest) : AutenticacaoResponse
}