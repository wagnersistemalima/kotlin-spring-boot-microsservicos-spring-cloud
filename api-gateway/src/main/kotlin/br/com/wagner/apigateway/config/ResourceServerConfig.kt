package br.com.wagner.apigateway.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.util.*

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

            http.cors().configurationSource(corsConfigurationSource())

    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource{
        val corsConfig = CorsConfiguration()

        val list = listOf("*")

        corsConfig.allowedOrigins(Arrays.asList("*"))     // quem vai poder acessar o sistema
        corsConfig.allowedOrigins(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"))
        corsConfig.allowCredentials(true)
        corsConfig.allowedHeaders(Arrays.asList("Authorization", "Content-Type"))

        val source = UrlBasedCorsConfigurationSource()

        source.registerCorsConfiguration("/**", corsConfig)
        return  source
    }

    @Bean
    fun corsFilter(): FilterRegistrationBean<CorsFilter> {
        var bean = FilterRegistrationBean<CorsFilter>(CorsFilter(corsConfigurationSource()))
        bean.order = Ordered.HIGHEST_PRECEDENCE
        return  bean
    }
}

private fun CorsConfiguration.allowCredentials(b: Boolean) {

}

private operator fun <E> MutableList<E>?.invoke(asList: List<E>) {

}


