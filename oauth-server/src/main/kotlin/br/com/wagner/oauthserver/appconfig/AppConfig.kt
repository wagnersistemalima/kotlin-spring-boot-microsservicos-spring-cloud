package br.com.wagner.oauthserver.appconfig

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
class AppConfig {

    // criar primeiro bean

    @Bean
    fun passowordEncode(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    // criar segundo bean pra mexer com JWT

    @Bean
    fun acessTokenConverter(): JwtAccessTokenConverter {
        var tokenCoverter: JwtAccessTokenConverter? = JwtAccessTokenConverter()
        tokenCoverter!!.setSigningKey("My-SECRET-KEY")
        return tokenCoverter
    }

    // criar terceiro bean para mexer com jwt, ler as informaçoes do token

    @Bean
    fun tokenStore(): JwtTokenStore {

        return JwtTokenStore(acessTokenConverter())

    }
}