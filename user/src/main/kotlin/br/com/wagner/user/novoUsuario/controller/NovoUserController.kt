package br.com.wagner.user.novoUsuario.controller

import br.com.wagner.user.novoUsuario.exceptions.ExceptionGenericValidated
import br.com.wagner.user.novoUsuario.repository.UserRepository
import br.com.wagner.user.novoUsuario.request.NovoUserRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("users")
class NovoUserController(
    @field:Autowired val userRepository: UserRepository,
    @field:Autowired val passwordEncoder: BCryptPasswordEncoder

    ) {

    val logger = LoggerFactory.getLogger(NovoUserController::class.java)

    @PostMapping
    fun insert(@Valid @RequestBody request: NovoUserRequest): ResponseEntity<Any> {

        logger.info("Iniciando cadastro de um usuario")

        if(userRepository.existsByEmail(request.email)) {
            throw ExceptionGenericValidated("Campo unico, email ja cadastrado")
        }

        val user = request.toModel(passwordEncoder)
        userRepository.save(user)


        logger.info("cadastro de um usuario realizado com sucesso")
        return ResponseEntity.ok().build()

    }
}