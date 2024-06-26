package com.example.drairlines.repository

import com.example.drairlines.data.datasource.NewDataSource
import com.example.drairlines.data.entity.AutenticacaoRequest
import com.example.drairlines.data.entity.AutenticacaoResponse
import javax.inject.Inject

class NewRepository @Inject constructor(
    private val newDataSource: NewDataSource
){

//    suspend fun criarUsuario( autenticacaoRequest: AutenticacaoRequest) : AutenticacaoResponse {
//
//    }
}