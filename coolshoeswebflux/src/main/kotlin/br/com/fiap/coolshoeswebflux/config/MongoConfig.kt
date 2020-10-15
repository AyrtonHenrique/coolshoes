package br.com.fiap.coolshoeswebflux.config

import br.com.fiap.coolshoeswebflux.repository.PessoaRepository
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = [PessoaRepository::class])
class MongoConfig: AbstractReactiveMongoConfiguration() {

    override fun getDatabaseName() = "coolshoesdb"

}

