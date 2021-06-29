package br.com.wagner.oauthserver.controller

import br.com.wagner.oauthserver.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class BuscarUsuarioController(@field:Autowired val service: UserService) {

    val logger = LoggerFactory.getLogger(BuscarUsuarioController::class.java)

    @GetMapping("/search")
    fun findByEmail(@RequestParam email: String): ResponseEntity<Any> {
        logger.info("........Iniciando a busca de usuario para autenticação.........")

        val usuario = service.consulta(email)


        logger.info("........Usuario autenticado retornado.......")
        return  ResponseEntity.ok().body(usuario)
    }
}