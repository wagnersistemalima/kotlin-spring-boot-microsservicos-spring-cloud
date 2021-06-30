package br.com.wagner.oauthserver.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ResourceExceptionHandller {

    // metodo para captar exceção de nao autorizado

    @ExceptionHandler(UsernameNotFoundException::class)
    fun notFound(e: UsernameNotFoundException, request: HttpServletRequest): ResponseEntity<Any> {
        val status = HttpStatus.UNAUTHORIZED
        val error = ValidationError(Instant.now(), status.value(), "Entity notFound", message = e.message!!, path = request.requestURI)
        return ResponseEntity.status(status).body(error)
    }

    // metodo para captar erro recurso nao encontrado

    @ExceptionHandler(ResourceNotFoundException::class)
    fun notFound(e: ResourceNotFoundException, request: HttpServletRequest): ResponseEntity<Any> {
        val status = HttpStatus.NOT_FOUND
        val error = ValidationError(Instant.now(), status.value(), "Entity notFound", message = e.message!!, path = request.requestURI)
        return ResponseEntity.status(status).body(error)
    }
}