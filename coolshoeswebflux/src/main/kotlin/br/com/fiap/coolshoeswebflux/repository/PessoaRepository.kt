package br.com.fiap.coolshoeswebflux.repository

import br.com.fiap.coolshoeswebflux.model.PessoaDocument
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface PessoaRepository: ReactiveMongoRepository<PessoaDocument, String>