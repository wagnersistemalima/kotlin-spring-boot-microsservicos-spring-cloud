package br.com.wagner.user

import br.com.wagner.user.novoUsuario.model.User
import br.com.wagner.user.novoUsuario.repository.UserRepository
import br.com.wagner.user.novoUsuario.request.NovoUserRequest
import com.fasterxml.jackson.databind.ObjectMapper
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
import java.net.URI

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@ActiveProfiles("test")
@Transactional
class novoUserControllerTest {

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
    @DisplayName("deve retornar 200, cadastrar um novo usuario")
    fun deveRetornar200CadastrarUser() {

        // cenario

        val uri = URI("/users")

        val request = NovoUserRequest(nome = "Carol", email = "carol@gmail.com", password = "123456")

        //ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(200))

        /// assertivas
    }

    // 2 cenario de teste

    @Test
    @DisplayName("deve retornar 400, ao tentar cadastrar um novo usuario com nome vazio")
    fun deveRetornar400CadastrarUserNomevazio() {

        // cenario

        val uri = URI("/users")

        val request = NovoUserRequest(nome = "", email = "carol@gmail.com", password = "123456")

        //ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        /// assertivas
    }


    // 3 cenario de teste

    @Test
    @DisplayName("deve retornar 400, ao tentar cadastrar um novo usuario com email invalido")
    fun deveRetornar400CadastrarUserEmailInvalido() {

        // cenario

        val uri = URI("/users")

        val request = NovoUserRequest(nome = "Carol", email = "carolgmail.com", password = "123456")

        //ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        /// assertivas
    }


    // 3 cenario de teste

    @Test
    @DisplayName("deve retornar 400, ao tentar cadastrar um novo usuario com email vazio")
    fun deveRetornar400CadastrarUserEmailVazio() {

        // cenario

        val uri = URI("/users")

        val request = NovoUserRequest(nome = "Carol", email = "", password = "123456")

        //ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))

        /// assertivas
    }

    // 3 cenario de teste

    @Test
    @DisplayName("deve retornar 400, ao tentar cadastrar um novo usuario com email ja cadastrado")
    fun deveRetornar400CadastrarUserEmailExistente() {

        // cenario

        val user = User(nome = "Maria", email = "maria@gmail.com", passwords = "123456")
        userRepository.save(user)

        val uri = URI("/users")

        val request = NovoUserRequest(nome = "Carol", email = "maria@gmail.com", password = "123456")

        //ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
            .andExpect(MockMvcResultMatchers.status().`is`(400))


        /// assertivas
    }




    // metodo para desserializar objeto da request

    fun toJson(request: NovoUserRequest): String {
        return objectMapper.writeValueAsString(request)
    }
}