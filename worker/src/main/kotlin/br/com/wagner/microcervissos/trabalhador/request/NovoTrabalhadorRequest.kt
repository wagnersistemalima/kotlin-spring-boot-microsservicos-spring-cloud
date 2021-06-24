package br.com.wagner.microcervissos.trabalhador.request

import br.com.wagner.microcervissos.trabalhador.model.Trabalhador
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class NovoTrabalhadorRequest(

    @field:NotBlank
    val nome: String,

    @field:CPF
    @field:NotBlank
    val cpf: String,

    @field:Positive
    @field:NotNull
    val rendaDiaria: BigDecimal

) {

    // metodo para converter dados da requisição em entidade

    fun toModel(): Trabalhador {

        return  Trabalhador(nome, cpf, rendaDiaria)
    }
}
