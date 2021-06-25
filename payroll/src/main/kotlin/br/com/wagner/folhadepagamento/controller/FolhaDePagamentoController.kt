package br.com.wagner.folhadepagamento.controller

import br.com.wagner.folhadepagamento.model.Pagamento
import br.com.wagner.folhadepagamento.service.FolhaDePagamentoService
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/pagamentos")
class FolhaDePagamentoController(@field:Autowired val service: FolhaDePagamentoService) {

    val logger = LoggerFactory.getLogger(FolhaDePagamentoController::class.java)

    // end point consultar folha de pagamento de um trabalhador

    @HystrixCommand(fallbackMethod = "consultaAlternativa")
    @GetMapping("/{idTrabalhador}/dias/{dias}")
    fun calcula(@PathVariable("idTrabalhador") idTrabalhador: Long, @PathVariable("dias") dias: Int) : ResponseEntity<Any>{
        logger.info("iniciando o calculo da folha de pagamento...")

        // chamada para o serviço de trabalhadores

        val pagamento = service.consulta(idTrabalhador, dias)

        logger.info("folha de pagamento para o trabalhador informado concluido com sucesso")
        return ResponseEntity.ok().body(pagamento)

    }

    fun consultaAlternativa(idTrabalhador: Long, dias: Int): ResponseEntity<Any> {

        // pagamento mockado

        val pagamento = Pagamento(id = 3000, nome = "Brann", rendaDiaria = BigDecimal(400.0), quantDias = dias)
        return ResponseEntity.ok().body(pagamento)
    }
}