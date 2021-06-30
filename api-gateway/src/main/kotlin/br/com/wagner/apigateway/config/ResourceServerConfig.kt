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

    var PUBLIC: Array<String> = arrayOf("/oauth/oauth/token")  // rota para fazer autenticação
    var OPERATOR: Array<String> = arrayOf("/worker/**")    // rota autorizada para operador sera de trabalhador
    var ADMIN: Array<String> = arrayOf("/payroll/**", "/user/**")   // para acessar qualquer rota de folha de pagamento e rota usuario tera perfil de admin



    override fun configure(resources: ResourceServerSecurityConfigurer?) {
        resources!!.tokenStore(tokenStore)
    }

    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
            .antMatchers("/oauth/oauth/token").permitAll()
            .antMatchers(HttpMethod.GET, "/worker/**").hasAnyRole("OPERATOR", "ADMIN")
            .antMatchers("/payroll/**").hasRole("ADMIN")
            .antMatchers("/user/**").hasRole("ADMIN")
            .anyRequest().authenticated()

    }
}