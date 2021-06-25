package br.com.wagner.worker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class WorkerApplication

fun main(args: Array<String>) {
	runApplication<WorkerApplication>(*args)
}
