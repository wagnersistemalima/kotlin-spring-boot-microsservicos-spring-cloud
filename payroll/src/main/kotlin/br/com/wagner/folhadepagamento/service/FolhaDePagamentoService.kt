package br.com.wagner.folhadepagamento.service

import br.com.wagner.folhadepagamento.apiExterna.ApiWorkerFeingClient
import br.com.wagner.folhadepagamento.exception.ResourceNotFoundException
import br.com.wagner.folhadepagamento.model.Pagamento
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception

// classe contendo a logica para chamada do microsserviço trabalhadores

@Service
class FolhaDePagamentoService(
    @field:Autowired val apiWorkerFeingClient: ApiWorkerFeingClient
) {

    val logger = LoggerFactory.getLogger(FolhaDePagamentoService::class.java)

    // metodo que realiza a logica de consultar api externa e calcular a folha

    fun consulta(idTrabalhador: Long, dias: Int): Pagamento {

        logger.info("execultando a logica para calcular a folha de pagamento")

        try {
            val response = apiWorkerFeingClient.findById(idTrabalhador).body

            val pagamento = Pagamento(response!!.id, response.nome, response.rendaDiaria, dias)

            logger.info("retornando a folha de pagamento do trabalhador informado")
            return pagamento
        }
        catch (erro: Exception) {
            logger.error("Entrou no cath, api externa retornou 404, recurso não encontrado")
            throw ResourceNotFoundException("recurso não encontrado")
        }

    }

}