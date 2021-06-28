package br.com.wagner.oauthserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class OauthServerApplication

fun main(args: Array<String>) {
	runApplication<OauthServerApplication>(*args)
}
