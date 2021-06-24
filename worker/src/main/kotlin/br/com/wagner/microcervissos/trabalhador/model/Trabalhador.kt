package br.com.wagner.microcervissos.trabalhador.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
class Trabalhador(

    val nome: String,

    @field:Column(unique = true)
    val cpf: String,

    val rendaDiaria: BigDecimal
){

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Trabalhador

        if (cpf != other.cpf) return false

        return true
    }

    override fun hashCode(): Int {
        return cpf.hashCode()
    }
}