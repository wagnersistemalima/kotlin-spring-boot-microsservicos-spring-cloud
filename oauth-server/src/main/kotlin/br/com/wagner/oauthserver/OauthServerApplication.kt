package br.com.wagner.oauthserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OauthServerApplication

fun main(args: Array<String>) {
	runApplication<OauthServerApplication>(*args)
}
