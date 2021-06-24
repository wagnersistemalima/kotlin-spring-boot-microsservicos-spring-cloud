package br.com.wagner.worker

import br.com.wagner.worker.trabalhador.model.Trabalhador
import br.com.wagner.worker.trabalhador.repository.TrabalhadorRepository
import br.com.wagner.worker.trabalhador.request.DeleteTrabalhadorRequest
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
class DeletaTrabalhadorControllerTest {

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
    @DisplayName("deve retornar no content 204, deletar um trabalhador pelo id")
    fun deveRetornar204NoContent() {
        // cenario

        val trabalhador = Trabalhador(nome = "wagner", cpf = "04394450438", rendaDiaria = BigDecimal(50.0))
        trabalhadorRepository.save(trabalhador)

        val uri = UriComponentsBuilder.fromUriString("/trabalhadores/{id}").buildAndExpand(trabalhador.id).toUri()

        val request = DeleteTrabalhadorRequest(cpf = trabalhador.cpf)

        // ação

        mockMvc.perform(MockMvcRequestBuilders.delete(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(204))


        // assertivas
    }

    // 2 cenario de teste

    @Test
    @DisplayName("deve retornar 404, deletar um trabalhador com id nao existe")
    fun deveRetornar404AoTentarDeletarTrabalhadoridnaoExiste() {
        // cenario


        val uri = UriComponentsBuilder.fromUriString("/trabalhadores/{id}").buildAndExpand(5000).toUri()

        val request = DeleteTrabalhadorRequest(cpf = "04394450438")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.delete(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(404))


        // assertivas
    }

    // 3 cenario de teste

    @Test
    @DisplayName("deve retornar 400, ao tentar deletar um trabalhador com cpf invalido")
    fun deveRetornar400AoTentarDeletarTrabalhadorCpfInvalido() {
        // cenario

        val trabalhador = Trabalhador(nome = "wagner", cpf = "04394450438", rendaDiaria = BigDecimal(50.0))
        trabalhadorRepository.save(trabalhador)

        val uri = UriComponentsBuilder.fromUriString("/trabalhadores/{id}").buildAndExpand(trabalhador.id).toUri()

        val request = DeleteTrabalhadorRequest(cpf = "043944504gfd")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.delete(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))


        // assertivas
    }

    // 4 cenario de teste

    @Test
    @DisplayName("deve retornar 400, ao tentar deletar um trabalhador com cpf valido de outra pessoa")
    fun deveRetornar400AoTentarDeletarTrabalhadorCpfValidoDeOutraPessoa() {
        // cenario

        val trabalhador = Trabalhador(nome = "wagner", cpf = "04394450438", rendaDiaria = BigDecimal(50.0))
        trabalhadorRepository.save(trabalhador)

        val uri = UriComponentsBuilder.fromUriString("/trabalhadores/{id}").buildAndExpand(trabalhador.id).toUri()

        val request = DeleteTrabalhadorRequest(cpf = "09424626452")

        // ação

        mockMvc.perform(MockMvcRequestBuilders.delete(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))


        // assertivas
    }


    // metodo para desserializar objeto de request

    fun toJson(request: DeleteTrabalhadorRequest): String {
        return objectMapper.writeValueAsString(request)
    }

}