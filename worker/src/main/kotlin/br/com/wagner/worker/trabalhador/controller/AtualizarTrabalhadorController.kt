package br.com.wagner.worker.trabalhador.controller

import br.com.wagner.worker.exceptions.ExceptionGenericValidated
import br.com.wagner.worker.exceptions.ResourceNotFoundException
import br.com.wagner.worker.trabalhador.repository.TrabalhadorRepository
import br.com.wagner.worker.trabalhador.request.AtualizaTrabalhadorRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/trabalhadores")
class AtualizarTrabalhadorController(@field:Autowired val trabalhadorRepository: TrabalhadorRepository) {

    val logger = LoggerFactory.getLogger(AtualizarTrabalhadorController::class.java)

    // end point atualizar trabalhador

    @Transactional
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @Valid @RequestBody request: AtualizaTrabalhadorRequest): ResponseEntity<Any> {
        logger.info("Iniciando atualização de um trabalhador")

        val possivelTrabalhador = trabalhadorRepository.findById(id)

        // validação

        if(possivelTrabalhador.isEmpty) {
            logger.error("Entrou no if, id do trabalhador não encontrado")
            throw ResourceNotFoundException("id do trabalhador não encontrado")
        }


        val trabalhador = possivelTrabalhador.get()

        // validação

        if(!trabalhador.cpf.equals(request.cpf)) {
            logger.info("Entrou no if, tentando atualizar um trabalhador com cpf de outro")
            throw ExceptionGenericValidated("vc esta tentando atualizar um trabalhador com cpf divergente do id")
        }

        trabalhador.rendaDiaria = request.rendaDiaria
        trabalhadorRepository.save(trabalhador)
        trabalhador.update()

        logger.info("Atualização do trabalhador concluida com sucesso")
        return ResponseEntity.ok().build()
    }

}