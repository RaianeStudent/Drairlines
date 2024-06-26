package com.example.drairlines.data

sealed class UIEvent{

    data class NomeAlterado(val nome: String) : UIEvent()
    data class EmailAlterado(val email: String) : UIEvent()
    data class CpfAlterado(val cpf: String) : UIEvent()
    data class SenhaAlterado(val senha: String) : UIEvent()

    object BotaoCadastrarApertado : UIEvent()
}
