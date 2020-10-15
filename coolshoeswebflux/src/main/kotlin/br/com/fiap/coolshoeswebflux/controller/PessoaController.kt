package br.com.fiap.coolshoeswebflux.controller

import br.com.fiap.coolshoeswebflux.dto.PessoaCreateDTO
import br.com.fiap.coolshoeswebflux.service.PessoaService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("pessoas")
class PessoaController(
        private val pessoaService: PessoaService
){

    @GetMapping(produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun listAll() = pessoaService.listAll()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody pessoaCreateDTO: PessoaCreateDTO) = pessoaService.create(pessoaCreateDTO)

}