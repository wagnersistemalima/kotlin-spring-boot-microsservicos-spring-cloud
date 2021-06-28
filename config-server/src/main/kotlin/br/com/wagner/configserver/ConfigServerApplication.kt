package br.com.wagner.configserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@EnableConfigServer
@SpringBootApplication
abstract class ConfigServerApplication


fun main(args: Array<String>) {
	runApplication<ConfigServerApplication>(*args)
}

