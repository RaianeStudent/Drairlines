package com.example.drairlines.data

data class CadastroUIEstado(
    var nome: String = "",
    var email: String = "",
    var cpf: String = "",
    var senha: String = "",


    var nomeError: Boolean = false,
    var emailError: Boolean = false,
    var cpfError: Boolean = false,
    var senhaError: Boolean = false


)