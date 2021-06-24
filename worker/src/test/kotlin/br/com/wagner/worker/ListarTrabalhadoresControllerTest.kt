package br.com.wagner.worker

import br.com.wagner.worker.trabalhador.model.Trabalhador
import br.com.wagner.worker.trabalhador.repository.TrabalhadorRepository
import br.com.wagner.worker.trabalhador.response.ListagemTrabalhadorResponse
import com.fasterxml.jackson.databind.ObjectMapper
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
import java.math.BigDecimal
import java.net.URI
import java.util.stream.Stream

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@ActiveProfiles("test")
@Transactional
class ListarTrabalhadoresControllerTest {

    @field:Autowired
    lateinit var trabalhadorRepository: TrabalhadorRepository

    @field:Autowired
    lateinit var mockMvc: MockMvc

    @field:Autowired
    lateinit var objectMapper: ObjectMapper

    // 1 cenario de teste/ caminho feliz

    @Test
    @DisplayName("deve retornar 200, lista de trabalhadores")
    fun deveRetornar200ListaDeTrabalhadores() {
        // cenario

        val trabalhador1 = Trabalhador(nome = "Joao", cpf = "04394450438", rendaDiaria = BigDecimal(25.0))

        val trabalhador2 = Trabalhador(nome = "Maria", cpf = "68688116083", rendaDiaria = BigDecimal(40.0))

        trabalhadorRepository.save(trabalhador1)
        trabalhadorRepository.save(trabalhador2)

        val uri = URI("/trabalhadores")

        //ação

        val lista = trabalhadorRepository.findAll()

        val response = lista.stream().map { trabalhador -> ListagemTrabalhadorResponse(trabalhador) }

        mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().`is`(200))
            .andExpect(MockMvcResultMatchers.content().json(toJson(response)))

        //assertivas

    }

    // metodo para desserializar objeto de resposta

    fun toJson(response: Stream<ListagemTrabalhadorResponse>) : String {
        return  objectMapper.writeValueAsString(response)
    }
}