package br.com.wagner.user

import br.com.wagner.user.novoUsuario.model.User
import br.com.wagner.user.novoUsuario.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
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

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@ActiveProfiles("test")
@Transactional
class BuscarUserControllerTest {

    @field:Autowired
    lateinit var userRepository: UserRepository

    @field:Autowired
    lateinit var mockMvc: MockMvc

    @field:Autowired
    lateinit var objectMapper: ObjectMapper

    // rodar antes de cada teste

    @BeforeEach
    internal fun setUp() {
        userRepository.deleteAll()
    }

    // 1 cenario de teste/ caminho feliz

    @Test
    fun `deve retornar 200, buscar um usuario por id`() {

        // cenario

        val user = User(nome = "Maria", email = "maria@gmeil.com", password = "123456")
        userRepository.save(user)

        val uri = UriComponentsBuilder.fromUriString("/users/{id}").buildAndExpand(user.id).toUri()

        // ação

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().`is`(200))
            .andExpect(MockMvcResultMatchers.content().json(toJson(user)))

        // assertivas
    }

    // 2 cenario de teste

    @Test
    fun `deve retornar 404, buscar um usuario por id que não existe`() {

        // cenario

        val user = User(nome = "Maria", email = "maria@gmeil.com", password = "123456")
        userRepository.save(user)

        val uri = UriComponentsBuilder.fromUriString("/users/{id}").buildAndExpand(5000).toUri()

        // ação

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().`is`(404))


        // assertivas
    }

    // metodo para desserializar objetpo de resposta

    fun toJson(user: User) : String{
        return objectMapper.writeValueAsString(user)
    }


}