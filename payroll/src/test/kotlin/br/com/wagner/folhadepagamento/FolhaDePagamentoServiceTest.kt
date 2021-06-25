package br.com.wagner.folhadepagamento

import br.com.wagner.folhadepagamento.apiExterna.ApiWorkerFeingClient
import br.com.wagner.folhadepagamento.apiExterna.TrabalhadorResponse
import br.com.wagner.folhadepagamento.exception.ResourceNotFoundException
import br.com.wagner.folhadepagamento.model.Pagamento
import br.com.wagner.folhadepagamento.service.FolhaDePagamentoService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
class FolhaDePagamentoServiceTest {

    @field:InjectMocks
    lateinit var service: FolhaDePagamentoService

    @Mock
    lateinit var apiWorkerFeingClient: ApiWorkerFeingClient

    // 1 cenario de teste/ caminho feliz

    @Test
    @DisplayName("a consulta deve retornar um pagamento de um trabalhador")
    fun consultaDeveRetornarUmPagamentoDoTrabalhador() {

        // cenario

        val quantDias = 5


        val pagamento = Pagamento(id = 1L, nome = "Macos", rendaDiaria = BigDecimal(20.0), quantDias = 5)

        //ação

        Mockito.`when`(apiWorkerFeingClient.findById(Mockito.anyLong())).thenReturn(ResponseEntity.ok().body(pagamento))

        // assertivas

        Assertions.assertDoesNotThrow {  service.consulta(idTrabalhador = pagamento.id, dias = quantDias) }
    }

    // 2 cenario de teste

    @Test
    @DisplayName("a consulta deve retornar exception 404, quando não encontrar id do trabalhador")
    fun consultaDeveRetornar404QuandoIdInexistente() {

        // cenario

        val quantDias = 5

        val idInexistente = 5000L

        //ação

        Mockito.`when`(apiWorkerFeingClient.findById(Mockito.anyLong())).thenReturn(ResponseEntity.notFound().build())

        // assertivas

        Assertions.assertThrows(ResourceNotFoundException::class.java) {service.consulta(idInexistente, quantDias)}
    }

}