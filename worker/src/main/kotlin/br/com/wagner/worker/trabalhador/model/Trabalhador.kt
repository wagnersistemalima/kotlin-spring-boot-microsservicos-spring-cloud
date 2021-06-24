package br.com.wagner.worker.trabalhador.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Trabalhador(

    val nome: String,

    @field:Column(unique = true)
    val cpf: String,

    var rendaDiaria: BigDecimal
){

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val dataRegistro: LocalDateTime = LocalDateTime.now()

    var updateDataRegistro: LocalDateTime? = null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Trabalhador

        if (cpf != other.cpf) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cpf.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }

    // metodo auxiliar para sempre que for atualizar um trabalhador armazenar o registro

    @PostUpdate
    fun update() {
        updateDataRegistro = LocalDateTime.now()
    }


}