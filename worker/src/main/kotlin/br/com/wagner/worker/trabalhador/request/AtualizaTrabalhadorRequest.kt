package br.com.wagner.worker.trabalhador.request

import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class AtualizaTrabalhadorRequest(

    @field:NotNull
    @field:Positive
    val rendaDiaria: BigDecimal,

    @field:CPF
    @field:NotBlank
    val cpf: String
)
