package br.com.wagner.folhadepagamento.apiExterna

import br.com.wagner.folhadepagamento.model.Pagamento
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Component
@FeignClient(name = "worker", url = "localhost:8001", path = "/trabalhadores")
interface ApiWorkerFeingClient {

    // end point
    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<Pagamento>
}