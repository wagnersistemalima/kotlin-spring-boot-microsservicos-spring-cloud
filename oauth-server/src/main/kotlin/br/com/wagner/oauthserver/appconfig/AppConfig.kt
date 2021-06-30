package br.com.wagner.oauthserver.appconfig

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
class AppConfig{



    @field:Value("\${jwt.secret}")
    var jwtSecret: String? = ""

    // criar primeiro bean

    @Bean
    fun passowordEncode(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    // criar segundo bean pra mexer com JWT

    @Bean
    fun acessTokenConverter(): JwtAccessTokenConverter {

        var tokenCoverter: JwtAccessTokenConverter? = JwtAccessTokenConverter()
        tokenCoverter!!.setSigningKey(jwtSecret)
        return tokenCoverter
    }

    // criar terceiro bean para mexer com jwt, ler as informaçoes do token

    @Bean
    fun tokenStore(): JwtTokenStore {

        return JwtTokenStore(acessTokenConverter())

    }
}