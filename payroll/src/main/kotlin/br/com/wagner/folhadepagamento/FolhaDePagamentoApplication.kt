package br.com.wagner.folhadepagamento

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class FolhaDePagamentoApplication

fun main(args: Array<String>) {
	runApplication<FolhaDePagamentoApplication>(*args)
}
