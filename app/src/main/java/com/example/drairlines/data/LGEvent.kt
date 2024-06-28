package com.example.drairlines.data

sealed class LGEvent{

    data class EmailAlterado(val email: String) : LGEvent()
    data class SenhaAlterado(val senha: String) : LGEvent()

    object BotaoLoginApertado : LGEvent()
}