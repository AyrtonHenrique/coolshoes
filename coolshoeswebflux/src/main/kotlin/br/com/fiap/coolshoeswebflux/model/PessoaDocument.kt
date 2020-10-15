package br.com.fiap.coolshoeswebflux.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class PessoaDocument (
        @Id
        val id: String,
        val nome: String,
        val cpf: String? = null
)