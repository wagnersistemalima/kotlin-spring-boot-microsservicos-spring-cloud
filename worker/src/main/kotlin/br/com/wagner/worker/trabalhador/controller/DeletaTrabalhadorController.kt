package br.com.wagner.worker.trabalhador.controller

import br.com.wagner.worker.exceptions.ExceptionGenericValidated
import br.com.wagner.worker.exceptions.ResourceNotFoundException
import br.com.wagner.worker.trabalhador.repository.TrabalhadorRepository
import br.com.wagner.worker.trabalhador.request.DeleteTrabalhadorRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("trabalhadores")
class DeletaTrabalhadorController(@field:Autowired val trabalhadorRepository: TrabalhadorRepository) {

    val logger = LoggerFactory.getLogger(DeletaTrabalhadorController::class.java)

    // end point delete

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long,@Valid @RequestBody request: DeleteTrabalhadorRequest): ResponseEntity<Any> {
        logger.info("Iniciando a deleção de um trabalhador pelo id")

        val possivelTrabalhador = trabalhadorRepository.findById(id)

        // validação

        if(possivelTrabalhador.isEmpty) {
            throw ResourceNotFoundException("id do trabalhador não encontrado")
        }

        val trabalhador = possivelTrabalhador.get()

        // validação

        if(!trabalhador.cpf.equals(request.cpf)) {
            throw ExceptionGenericValidated("Violação de integridade, vc esta tentando deletar um trabalhador com cpf divergente")
        }

        trabalhadorRepository.deleteById(trabalhador.id!!)

        return ResponseEntity.noContent().build()
    }
}