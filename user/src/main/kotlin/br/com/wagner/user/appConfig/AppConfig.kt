package br.com.wagner.user.appConfig

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

// classe responsavel por criar bean

@Configuration
class AppConfig {

    // metodo

    @Bean
    fun passowordEncode(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}