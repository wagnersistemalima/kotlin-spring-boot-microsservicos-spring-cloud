package br.com.wagner.worker

import br.com.wagner.worker.trabalhador.model.Trabalhador
import br.com.wagner.worker.trabalhador.repository.TrabalhadorRepository
import br.com.wagner.worker.trabalhador.request.AtualizaTrabalhadorRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
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
class AtualizarTrabalhadorControllerTest {

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

    // 1 cenario de testes / caminho feliz

    @Test
    @DisplayName("deve retornar 200, atualizar a renda diaria do trabalhador")
    fun deveRetornar200AtualizarRendaDiaria() {

        // cenario

        val trabalhador = Trabalhador(nome = "wagner", cpf = "04394450438", rendaDiaria = BigDecimal(50.0))
        trabalhadorRepository.save(trabalhador)

        val uri = UriComponentsBuilder.fromUriString("/trabalhadores/{id}").buildAndExpand(trabalhador.id).toUri()

        // ação

        val request = AtualizaTrabalhadorRequest(BigDecimal(80.0), cpf = trabalhador.cpf)

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(200))

        //assertivas

        Assertions.assertEquals(trabalhador.rendaDiaria, request.rendaDiaria)
    }

    // 2 cenario de testes

    @Test
    @DisplayName("deve retornar 400, ao tentar atualizar a renda diaria, informando um cpf de outro trabalhador")
    fun deveRetornar400AoTentarAtualizarRendaDiariaDeTrabalhadorInformandoCpfDeoutro() {

        // cenario

        val trabalhador1 = Trabalhador(nome = "wagner", cpf = "04394450438", rendaDiaria = BigDecimal(50.0))
        trabalhadorRepository.save(trabalhador1)

        val trabalhador2 = Trabalhador(nome = "carol", cpf = "09424626452", rendaDiaria = BigDecimal(20.0))
        trabalhadorRepository.save(trabalhador2)


        val uri = UriComponentsBuilder.fromUriString("/trabalhadores/{id}").buildAndExpand(trabalhador2.id).toUri()

        // ação

        val request = AtualizaTrabalhadorRequest(BigDecimal(80.0), cpf = trabalhador1.cpf)

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        //assertivas


    }

    // 3 cenario de testes

    @Test
    @DisplayName("deve retornar 404, ao tentar atualizar a renda diaria, informando um id que não existe")
    fun deveRetornar404AoTentarAtualizarRendaDiariaDeTrabalhadorInformandoIdInexistente() {

        // cenario

        val trabalhador1 = Trabalhador(nome = "wagner", cpf = "04394450438", rendaDiaria = BigDecimal(50.0))
        trabalhadorRepository.save(trabalhador1)


        val uri = UriComponentsBuilder.fromUriString("/trabalhadores/{id}").buildAndExpand(5000).toUri()

        // ação

        val request = AtualizaTrabalhadorRequest(BigDecimal(80.0), cpf = trabalhador1.cpf)

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(404))

        //assertivas


    }

    // 4 cenario de testes

    @Test
    @DisplayName("deve retornar 400, ao tentar atualizar a renda diaria, informando um cpf invalido")
    fun deveRetornar404AoTentarAtualizarRendaDiariaDeTrabalhadorInformandoCpfInvalido() {

        // cenario

        val trabalhador1 = Trabalhador(nome = "wagner", cpf = "04394450438", rendaDiaria = BigDecimal(50.0))
        trabalhadorRepository.save(trabalhador1)


        val uri = UriComponentsBuilder.fromUriString("/trabalhadores/{id}").buildAndExpand(trabalhador1.id).toUri()

        // ação

        val request = AtualizaTrabalhadorRequest(BigDecimal(80.0), cpf = "043944poiu")

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        //assertivas


    }

    // 5 cenario de testes

    @Test
    @DisplayName("deve retornar 400, ao tentar atualizar a renda diaria, informando valor 0.0")
    fun deveRetornar404AoTentarAtualizarRendaDiariaDeTrabalhadorInformandoValorZero() {

        // cenario

        val trabalhador1 = Trabalhador(nome = "wagner", cpf = "04394450438", rendaDiaria = BigDecimal(50.0))
        trabalhadorRepository.save(trabalhador1)


        val uri = UriComponentsBuilder.fromUriString("/trabalhadores/{id}").buildAndExpand(trabalhador1.id).toUri()

        // ação

        val request = AtualizaTrabalhadorRequest(BigDecimal(0.0), trabalhador1.cpf)

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        //assertivas


    }


    // desserializar objeto da requisição

    fun toJson(request: AtualizaTrabalhadorRequest): String {
        return objectMapper.writeValueAsString(request)
    }


}