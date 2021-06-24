package br.com.wagner.worker.trabalhador.response

import br.com.wagner.worker.trabalhador.model.Trabalhador

class ListagemTrabalhadorResponse(

    val id: Long?,
    val nome: String
){
    constructor(trabalhador: Trabalhador): this(trabalhador.id, trabalhador.nome)
}
