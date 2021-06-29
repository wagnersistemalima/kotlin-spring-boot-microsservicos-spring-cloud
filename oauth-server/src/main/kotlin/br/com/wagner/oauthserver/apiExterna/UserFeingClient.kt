package br.com.wagner.oauthserver.apiExterna

import br.com.wagner.user.novoUsuario.model.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*


@Component
@FeignClient(name = "user", path = "/users")
interface UserFeingClient {

    // end point

    @GetMapping("/search")
    fun findByemail(@RequestParam email: String): ResponseEntity<User>

}