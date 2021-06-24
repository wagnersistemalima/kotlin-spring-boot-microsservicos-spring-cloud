package br.com.wagner.worker.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ResourceExceptionHandller {

    // metodo para captar exceção de validação

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validation(e: MethodArgumentNotValidException, request: HttpServletRequest) : ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val error = ValidationError(Instant.now(), status.value(),error = "Validation exception", message = e.message, path = request.requestURI)

        for(f: FieldError in e.bindingResult.fieldErrors ) {
            error.addError(f.field, f.defaultMessage!!)
        }

        return ResponseEntity.status(status).body(error)
    }

    // metodo para captar exceção de campo unico

    @ExceptionHandler(ExceptionGenericValidated::class)
    fun campoUnico(e: ExceptionGenericValidated, request: HttpServletRequest): ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val error = ValidationError(Instant.now(), status.value(), "Validation", message = e.message!!, path = request.requestURI)

        return  ResponseEntity.status(status).body(error)
    }

    // metodo para tratar exception de recurso não encontrado

    @ExceptionHandler(ResourceNotFoundException::class)
    fun campoUnico(e: ResourceNotFoundException, request: HttpServletRequest): ResponseEntity<Any> {
        val status = HttpStatus.NOT_FOUND
        val error = ValidationError(Instant.now(), status.value(), "Recurso não encontrado", message = e.message!!, path = request.requestURI)

        return  ResponseEntity.status(status).body(error)
    }
}