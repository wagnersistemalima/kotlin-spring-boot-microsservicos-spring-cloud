package br.com.wagner.worker.trabalhador.controller

import br.com.wagner.worker.trabalhador.repository.TrabalhadorRepository
import br.com.wagner.worker.trabalhador.response.ListagemTrabalhadorResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@RestController
@RequestMapping("/trabalhadores")
class ListarTrabalhadoresController(@field:Autowired val trabalhadorRepository: TrabalhadorRepository) {

    val logger =  LoggerFactory.getLogger(ListarTrabalhadoresController::class.java)

    @Transactional
    @GetMapping
    fun findAll(): ResponseEntity<Any> {
        logger.info("Iniciando a listagem de trabalhadores")

        val lista = trabalhadorRepository.findAll()


        logger.info("Retornado a lista de trabalhadores com sucesso")
        return ResponseEntity.ok().body(lista.stream().map { trabalhador -> ListagemTrabalhadorResponse(trabalhador) }.collect(Collectors.toList()))
    }

}