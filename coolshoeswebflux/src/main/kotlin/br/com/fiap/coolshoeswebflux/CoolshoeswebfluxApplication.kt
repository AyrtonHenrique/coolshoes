package br.com.fiap.coolshoeswebflux

import br.com.fiap.coolshoeswebflux.config.CloudConfigProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(CloudConfigProperties::class)
class CoolshoeswebfluxApplication

fun main(args: Array<String>) {
	runApplication<CoolshoeswebfluxApplication>(*args)
}
