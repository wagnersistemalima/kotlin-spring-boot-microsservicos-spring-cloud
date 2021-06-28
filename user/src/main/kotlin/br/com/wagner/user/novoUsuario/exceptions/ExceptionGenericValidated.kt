package br.com.wagner.user.novoUsuario.exceptions

import java.lang.RuntimeException

class ExceptionGenericValidated(val msg: String): RuntimeException(msg) {
}