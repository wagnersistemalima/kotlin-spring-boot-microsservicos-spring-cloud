package br.com.wagner.worker

import br.com.wagner.worker.trabalhador.model.Trabalhador
import br.com.wagner.worker.trabalhador.repository.TrabalhadorRepository
import br.com.wagner.worker.trabalhador.request.NovoTrabalhadorRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
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

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@ActiveProfiles("test")
@Transactional
class NovoTrabalhadorControllerTest {

    @field:Autowired
    lateinit var trabalhadorRepository: TrabalhadorRepository

    @field:Autowired
    lateinit var mockMvc: MockMvc

    @field:Autowired
    lateinit var objectMapper: ObjectMapper

    @BeforeEach
    internal fun setUp() {
        trabalhadorRepository.deleteAll()
    }

    // rodar depois de cada teste
    @AfterEach
    internal fun tearDown() {


    }

    // 1 cenario de teste/ caminho feliz

    @Test
    fun deveRetornar200CadastrarTrabalhador() {

        // cenario

        val uri = URI("/trabalhadores")

        val request = NovoTrabalhadorRequest(nome = "trabalhador1", cpf = "68688116083", rendaDiaria = BigDecimal(50.0))

        //ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(200))

        //assertivas
    }

    // 2 cenario de teste

    @Test
    @DisplayName("deve lançar exception, quando tentar cadastrar trabalhador com cpf ja cadastrado")
    fun deveRetornar400TentarCadastrarTrabalhadorComCpfJaCadastrado() {

        // cenario

        val trabalhador = Trabalhador(nome = "Pedro", cpf = "04394450438", rendaDiaria = BigDecimal(50.0))
        trabalhadorRepository.save(trabalhador)

        val uri = URI("/trabalhadores")

        val request = NovoTrabalhadorRequest(nome = "trabalhador1", cpf = "04394450438", rendaDiaria = BigDecimal(50.0))

        //ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        //assertivas
    }

    // 3 cenario de teste

    @Test
    @DisplayName("deve lançar exception, quando tentar cadastrar trabalhador com renda diaria negativa")
    fun deveRetornar400TentarCadastrarTrabalhadorComRendaNegativa() {

        // cenario

        val uri = URI("/trabalhadores")

        val request = NovoTrabalhadorRequest(nome = "trabalhador1", cpf = "04394450438", rendaDiaria = BigDecimal(-50.0))

        //ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        //assertivas
    }

    // 5 cenario de teste

    @Test
    @DisplayName("deve lançar exception, quando tentar cadastrar trabalhador com cpf vazio")
    fun deveRetornar400TentarCadastrarTrabalhadorComCpfVazio() {

        // cenario

        val uri = URI("/trabalhadores")

        val request = NovoTrabalhadorRequest(nome = "trabalhador1", cpf = "", rendaDiaria = BigDecimal(50.0))

        //ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        //assertivas
    }

    // 6 cenario de teste

    @Test
    @DisplayName("deve lançar exception, quando tentar cadastrar trabalhador com cpf invalido")
    fun deveRetornar400TentarCadastrarTrabalhadorComCpfInvalido() {

        // cenario

        val uri = URI("/trabalhadores")

        val request = NovoTrabalhadorRequest(nome = "trabalhador1", cpf = "043944", rendaDiaria = BigDecimal(50.0))

        //ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        //assertivas
    }


    // metodo para desserializar objeto request

    fun toJson(request: NovoTrabalhadorRequest): String {
        return objectMapper.writeValueAsString(request)
    }


}