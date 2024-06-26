package com.example.drairlines.data.entity

data class AutenticacaoResponse(
    val token: String
)

data class AutenticacaoRequest(
    val nome:String,
    val cpf:String,
    val email: String,
    val senha:String
)
