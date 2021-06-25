package br.com.wagner.folhadepagamento

import br.com.wagner.folhadepagamento.apiExterna.ApiWorkerFeingClient
import br.com.wagner.folhadepagamento.model.Pagamento
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.util.UriComponentsBuilder
import java.math.BigDecimal

@SpringBootTest
@AutoConfigureMockMvc
class FolhaDePagamentoControllerTest {

    @field:MockBean
    lateinit var apiWorkerFeingClient: ApiWorkerFeingClient

    @field:Autowired
    lateinit var objectMapper: ObjectMapper

    @field:Autowired
    lateinit var mockMvc: MockMvc

    // 1 cenario de teste/ caminho feliz

    @Test
    @DisplayName("deve retornar 200, com o calculo da folha de pagamento do trabalhador")
    fun consultaDeveRetornar200() {

        // cenario

        val pagamento = Pagamento(id = 1L, nome = "Macos", rendaDiaria = BigDecimal(20.0), quantDias = 5)

        val uri = UriComponentsBuilder.fromUriString("/pagamentos/{idTrabalhador}/dias/{dias}").buildAndExpand(pagamento.id, pagamento.quantDias).toUri()

        // ação comportamento da api externa

        Mockito.`when`(apiWorkerFeingClient.findById(pagamento.id)).thenReturn(ResponseEntity.ok().body(pagamento))

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().`is`(200))
            .andExpect(MockMvcResultMatchers.content().json(toJson(pagamento)))

        //assertivas
    }

    // 2 cenario de teste

    @Test
    @DisplayName("deve retornar 404, quando não encontrar id do trabalhador")
    fun consultaDeveRetornar404() {

        // cenario


        val uri = UriComponentsBuilder.fromUriString("/pagamentos/{idTrabalhador}/dias/{dias}").buildAndExpand(5000, 5).toUri()

        // ação comportamento da api externa

        Mockito.`when`(apiWorkerFeingClient.findById(5000)).thenReturn(ResponseEntity.notFound().build())

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().`is`(404))


        //assertivas
    }

    // metodo para desserializar objeto de respose

    fun toJson(response: Pagamento): String {
        return objectMapper.writeValueAsString(response)
    }

}