package br.com.wagner.oauthserver.service

import br.com.wagner.oauthserver.apiExterna.UserFeingClient
import br.com.wagner.oauthserver.exception.ResourceNotFoundException
import br.com.wagner.user.novoUsuario.model.User
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@field:Autowired val apiExternaUser: UserFeingClient) {

    val logger = LoggerFactory.getLogger(UserService::class.java)

    // metodo para fazer a chamada da api externa

    fun consulta(email: String): User {

        logger.info(".....Iniciando a busca na api externa user por email $email .....")

        // chamada para api user

        try {
            val usuario = apiExternaUser.findByemail(email).body


            logger.info("retornando usuario da api externa")
            return usuario!!
        }
        catch (erro: Exception) {
            logger.error(".....Entrou no if, email $email não encontrado.....")
            throw ResourceNotFoundException(".....email usuario não encontrado.....")
        }

    }


}