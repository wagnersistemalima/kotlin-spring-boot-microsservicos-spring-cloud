package br.com.wagner.worker.trabalhador.request

import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.NotBlank

class DeleteTrabalhadorRequest(

    @field:NotBlank
    @field:CPF
    val cpf: String
)
