package br.com.wagner.user.novoUsuario.request

import br.com.wagner.user.novoUsuario.exceptions.ExceptionGenericValidated
import br.com.wagner.user.novoUsuario.model.User
import br.com.wagner.user.novoUsuario.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class NovoUserRequest(

    @field:NotBlank
    val nome: String,

    @field:NotBlank
    @field:Email
    val email: String,

    @NotBlank
    val password: String

) {

    // metodo para converter dados requisição em entidade
    fun toModel(passwordEncoder: BCryptPasswordEncoder): User {

        val hashSenha = passwordEncoder.encode(password)

        return User(nome, email, hashSenha)

    }
}
