package br.com.wagner.folhadepagamento.model

import java.math.BigDecimal

class Pagamento(

    val id: Long,
    val nome: String,
    val rendaDiaria: BigDecimal,
    val quantDias: Int
){

    // metodo para calcular folha de pagamento

    fun getTotal(): BigDecimal {
        val temp = BigDecimal(quantDias)
        return rendaDiaria.multiply(temp)
    }
}
