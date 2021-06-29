package br.com.wagner.user.novoUsuario.controller

import br.com.wagner.user.novoUsuario.exceptions.ResourceNotFoundException
import br.com.wagner.user.novoUsuario.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class BuscarUserController(@field:Autowired val userRepository: UserRepository) {

    val logger = LoggerFactory.getLogger(BuscarUserController::class.java)

    // end point buscar usuario por id

    @GetMapping("/{id}")
    fun findByid(@PathVariable("id") id: Long): ResponseEntity<Any> {
        logger.info("........Iniciando busca de usuario por id.........")

        val possivelUser = userRepository.findById(id)

        // validação

        if(possivelUser.isEmpty) {
            logger.error("...........Entrou no if, recurso não encontrado..........")
            throw ResourceNotFoundException("recurso não encontrado")
        }

        val user = possivelUser.get()

        logger.info(".........Busca por usuario pelo id realizada com sucesso..........")
        return ResponseEntity.ok().body(user)

    }

    // end point buscar por email

    @GetMapping("/search")
    fun findByemail(@RequestParam email: String): ResponseEntity<Any> {
        logger.info("....Iniciando a busca por usuario email $email ............")

        val possivelUser = userRepository.findByEmail(email)

        // validação

        if(possivelUser.isEmpty) {
            logger.error("...........Entrou no if, recurso não encontrado... $email ......")
            throw ResourceNotFoundException("recurso não encontrado")
        }

        val user = possivelUser.get()

        logger.info(" .........Busca por usuario pelo email realizada com sucesso..........")
        return  ResponseEntity.ok().body(user)

    }
}