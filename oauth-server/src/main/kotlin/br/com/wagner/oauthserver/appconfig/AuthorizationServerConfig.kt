package br.com.wagner.oauthserver.appconfig

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

// classe responsavel pela configuração do servidor de autorização

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig(

    @field:Autowired val passwordEncoder: BCryptPasswordEncoder,
    @field:Autowired val acesssTokenConverter: JwtAccessTokenConverter,
    @field:Autowired val jwtTokenStore: JwtTokenStore,
    @field:Autowired val authenticationManager: AuthenticationManager

): AuthorizationServerConfigurerAdapter() {

    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        security!!.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients!!.inMemory()
            .withClient("myappname123")
            .secret(passwordEncoder.encode("myappsecret123"))
            .scopes("read", "write")
            .authorizedGrantTypes("password")
            .accessTokenValiditySeconds(86400)   // vence em 24horas
    }

    // configurar o processamento do tocken

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints!!.authenticationManager(authenticationManager)
            .tokenStore(jwtTokenStore)
            .accessTokenConverter(acesssTokenConverter)
    }
}