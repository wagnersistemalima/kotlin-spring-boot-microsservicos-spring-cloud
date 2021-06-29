package br.com.wagner.user.novoUsuario.model


class User(

    val nome: String,

    val email: String,

    val password: String
){

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

}


