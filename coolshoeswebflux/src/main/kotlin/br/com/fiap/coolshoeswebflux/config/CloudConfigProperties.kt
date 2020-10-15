package br.com.fiap.coolshoeswebflux.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("propriedade")
class CloudConfigProperties {

    lateinit var remoteFile: String

}
