package br.com.wagner.worker.trabalhador.controller

import br.com.wagner.worker.exceptions.ResourceNotFoundException
import br.com.wagner.worker.trabalhador.repository.TrabalhadorRepository
import br.com.wagner.worker.trabalhador.response.DetalhesTrabalhadorResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/trabalhadores")
class DetalhesTrabalhadorController(@field:Autowired val trabalhadorRepository: TrabalhadorRepository) {

    val logger = LoggerFactory.getLogger(DetalhesTrabalhadorController::class.java)

    // end point findById / buscar por id

    @Transactional
    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<Any> {
        logger.info("iniciando a busca de um trabalhador por id")

        val possivelTrabalhador = trabalhadorRepository.findById(id)

        // validação

        if(possivelTrabalhador.isEmpty) {
            logger.error("Entrou no if, id do trabalhador não encontrado")
            throw ResourceNotFoundException("id do trabalhador não encontrado!")
        }

        val trabalhador = possivelTrabalhador.get()

        logger.info("detalhes do trabalhador enviados com sucesso")
        return ResponseEntity.ok().body(DetalhesTrabalhadorResponse(trabalhador))
    }
}