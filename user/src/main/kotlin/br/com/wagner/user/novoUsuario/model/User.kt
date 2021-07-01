package br.com.wagner.user.novoUsuario.model

import java.util.HashSet
import javax.persistence.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
@Table(name = "tb_user")
class User(

    val nome: String,

    @field:Column(unique = true)
    val email: String,

    val password: String
){

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    // associação muitos para muitos-> criação de uma terceira tabela com chave estrageira user e role

    @ManyToMany(fetch = FetchType.EAGER) // dados dos perfils carregado automativamente
    val roles: Set<Role> = HashSet<Role>()

    // equeals & hascode
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (email != other.email) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }

}


