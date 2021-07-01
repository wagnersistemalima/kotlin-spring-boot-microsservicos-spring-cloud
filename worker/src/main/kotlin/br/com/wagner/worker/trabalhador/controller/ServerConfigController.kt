package br.com.wagner.worker.trabalhador.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RefreshScope
@RestController
@RequestMapping("/trabalhadores")
class ServerConfigController() {

    val logger = LoggerFactory.getLogger(ServerConfigController::class.java)

    // end point para o servidor de configuração

    @GetMapping("/configs")
    fun getConfig(): ResponseEntity<Unit> {

        return  ResponseEntity.noContent().build()
    }
}