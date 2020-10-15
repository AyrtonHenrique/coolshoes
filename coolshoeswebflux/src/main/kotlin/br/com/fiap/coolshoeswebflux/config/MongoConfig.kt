package br.com.fiap.coolshoeswebflux.config

import br.com.fiap.coolshoeswebflux.repository.PessoaRepository
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = [PessoaRepository::class])
class MongoConfig: AbstractReactiveMongoConfiguration() {

    override fun getDatabaseName() = "coolshoesdb"

    override fun reactiveMongoClient(): MongoClient = MongoClients.create()

    @Bean
    fun mongoClient(): MongoClient = MongoClients.create()

    override fun reactiveMongoTemplate(databaseFactory: ReactiveMongoDatabaseFactory, mongoConverter: MappingMongoConverter) =
            ReactiveMongoTemplate(mongoClient(), databaseName)

}

