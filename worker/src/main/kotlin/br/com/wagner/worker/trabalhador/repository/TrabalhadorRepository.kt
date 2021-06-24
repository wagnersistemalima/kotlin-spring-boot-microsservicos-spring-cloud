package br.com.wagner.worker.trabalhador.repository

import br.com.wagner.worker.trabalhador.model.Trabalhador
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrabalhadorRepository: JpaRepository<Trabalhador, Long> {

    fun existsByCpf(cpf: String): Boolean
}