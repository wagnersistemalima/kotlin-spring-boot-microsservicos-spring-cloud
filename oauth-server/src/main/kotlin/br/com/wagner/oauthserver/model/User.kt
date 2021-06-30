package br.com.wagner.user.novoUsuario.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors


class User(val nome: String, val email: String, private val password: String) : UserDetails{

    var id: Long? = null

    // associação muitos para muitos

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

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles.stream().map { perfil -> SimpleGrantedAuthority(perfil.perfil) }.collect(Collectors.toList())
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}


