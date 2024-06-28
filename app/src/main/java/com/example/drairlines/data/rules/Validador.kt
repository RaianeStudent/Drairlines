package com.example.drairlines.data.rules

object Validador {


    fun validarNome(nome:String) : ResultadoValidacao{
        return ResultadoValidacao(
            (!nome.isNullOrEmpty() && nome.length >= 6)
        )
    }

    fun validarEmail(email:String) : ResultadoValidacao{
        return ResultadoValidacao(
            (!email.isNullOrEmpty())
        )
    }

    fun validarData(data:String) : ResultadoValidacao{
        return ResultadoValidacao(
            (data.isNotEmpty())
        )
    }

    fun validarCpf(cpf:String) : ResultadoValidacao{
        return ResultadoValidacao(
            (!cpf.isNullOrEmpty() && (cpf.length > 8 && cpf.length <= 11))
        )
    }

    fun validarSenha(senha:String) : ResultadoValidacao{
        return ResultadoValidacao(
            (!senha.isNullOrEmpty() && senha.length > 4)
        )
    }

}

data class ResultadoValidacao(
    val status : Boolean = false
)