package br.com.fiap.coolshoeswebflux.service

import br.com.fiap.coolshoeswebflux.dto.PessoaCreateDTO
import br.com.fiap.coolshoeswebflux.dto.PessoaDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PessoaService {

    fun create(pessoaCreateDTO: PessoaCreateDTO): Mono<PessoaDTO>
    fun listAll(): Flux<PessoaDTO>

}