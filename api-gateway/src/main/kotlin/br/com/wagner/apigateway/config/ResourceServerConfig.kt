package br.com.wagner.apigateway.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@EnableResourceServer        // anotação que vai configurar por baixo dos panos
@Configuration
class ResourceServerConfig(
    @field:Autowired val tokenStore: JwtTokenStore

): ResourceServerConfigurerAdapter() {


    override fun configure(resources: ResourceServerSecurityConfigurer?) {
        resources!!.tokenStore(tokenStore)
    }

    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
            .antMatchers("/oauth/oauth/token").permitAll()     // rota para fazer autenticação publica
            .antMatchers(HttpMethod.GET, "/worker/**").hasAnyRole("OPERATOR", "ADMIN")  // rota autorizada para perfil operator e admin, rota trabalhador
            .antMatchers("/payroll/**").hasRole("ADMIN")
            .antMatchers("/user/**").hasRole("ADMIN")
            .antMatchers("/actuator/**").hasRole("ADMIN")
            .antMatchers("/worker/actuator/**").hasRole("ADMIN")
            .antMatchers("/oauth/actuator/**").hasRole("ADMIN")
            .anyRequest().authenticated()

    }
}