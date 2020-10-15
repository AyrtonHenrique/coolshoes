package br.com.fiap.coolshoeswebflux.dto

data class PessoaCreateDTO(
        val nome: String,
        val cpf: String? = null
)
