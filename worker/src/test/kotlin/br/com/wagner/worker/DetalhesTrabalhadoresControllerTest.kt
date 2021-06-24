package br.com.wagner.worker

import br.com.wagner.worker.trabalhador.model.Trabalhador
import br.com.wagner.worker.trabalhador.repository.TrabalhadorRepository
import br.com.wagner.worker.trabalhador.response.DetalhesTrabalhadorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.util.UriComponentsBuilder
import java.math.BigDecimal

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@ActiveProfiles("test")
@Transactional
class DetalhesTrabalhadoresControllerTest {

    @field:Autowired
    lateinit var trabalhadorRepository: TrabalhadorRepository

    @field:Autowired
    lateinit var mockMvc: MockMvc

    @field:Autowired
    lateinit var objectMapper: ObjectMapper

    // rodar depois de cada teste
    @AfterEach
    internal fun tearDown() {
        trabalhadorRepository.deleteAll()
    }

    // 1 cenario de teste/ caminho feliz

    @Test
    @DisplayName("deve retornar 200, com detalhes de um trabalhador")
    fun deveRetornar200ComDetalhesDeUmTrabalhador() {

        // cenario

        val trabalhador = Trabalhador(nome = "Joao", cpf = "04394450438", rendaDiaria = BigDecimal(30.0))
        trabalhadorRepository.save(trabalhador)

        val uri = UriComponentsBuilder.fromUriString("/trabalhadores/{id}").buildAndExpand(trabalhador.id).toUri()

        // ação

        val response = DetalhesTrabalhadorResponse(trabalhador)

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().`is`(200))
            .andExpect(MockMvcResultMatchers.content().json(toJson(response)))

        // assertivas
    }

    // 2 cenario de teste

    @Test
    @DisplayName("deve retornar 404, lançar exception quando não encontrar id do trabalhador")
    fun deveRetornar404QuandoNaoEncontrarIdTrabalhador() {

        // cenario

        val trabalhador = Trabalhador(nome = "Joao", cpf = "04394450438", rendaDiaria = BigDecimal(30.0))
        trabalhadorRepository.save(trabalhador)

        val uri = UriComponentsBuilder.fromUriString("/trabalhadores/{id}").buildAndExpand(5000).toUri()

        // ação

        val response = DetalhesTrabalhadorResponse(trabalhador)

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().`is`(404))


        // assertivas
    }


    // metodo para desserializar objeto de resposta

    fun toJson(response: DetalhesTrabalhadorResponse): String {
        return  objectMapper.writeValueAsString(response)
    }
}