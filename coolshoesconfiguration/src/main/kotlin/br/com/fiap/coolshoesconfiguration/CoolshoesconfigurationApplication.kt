package br.com.fiap.coolshoesconfiguration

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class CoolshoesconfigurationApplication

fun main(args: Array<String>) {
	runApplication<CoolshoesconfigurationApplication>(*args)
}
