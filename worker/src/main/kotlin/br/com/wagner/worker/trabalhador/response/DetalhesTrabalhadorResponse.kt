package br.com.wagner.worker.trabalhador.response

import br.com.wagner.worker.trabalhador.model.Trabalhador
import java.math.BigDecimal
import java.time.LocalDateTime

class DetalhesTrabalhadorResponse(

    val id: Long?,
    val nome: String,
    val cpf: String,
    val rendaDiaria: BigDecimal,
    val dataRegistro: LocalDateTime?,
    val atualizacao: LocalDateTime?
){
    constructor(trabalhador: Trabalhador): this(trabalhador.id, trabalhador.nome, trabalhador.cpf, trabalhador.rendaDiaria, trabalhador.dataRegistro, trabalhador.updateDataRegistro)
}
