package br.com.wagner.worker.trabalhador.controller

import br.com.wagner.worker.exceptions.ExceptionGenericValidated
import br.com.wagner.worker.trabalhador.repository.TrabalhadorRepository
import br.com.wagner.worker.trabalhador.request.NovoTrabalhadorRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/trabalhadores")
class NovoTrabalhadorController(@field:Autowired val trabalhadorRepository: TrabalhadorRepository) {

    val logger = LoggerFactory.getLogger(NovoTrabalhadorController::class.java)

    // endPoint insert dados de um trabalhador

    @Transactional
    @PostMapping
    fun insert(@Valid @RequestBody request: NovoTrabalhadorRequest): ResponseEntity<Any> {
        logger.info("Iniciando cadastro de um novo trabalhador...")

        // validação campo unico

        if(trabalhadorRepository.existsByCpf(request.cpf)) {
            logger.error("Entrou no if, cpf já cadastrado")
            throw ExceptionGenericValidated("Campo unico, cpf já cadastrado")
        }

        val trabalhador = request.toModel()
        trabalhadorRepository.save(trabalhador)

        logger.info("Trabalhador cadastrado com sucesso")
        return ResponseEntity.ok().build()
    }
}