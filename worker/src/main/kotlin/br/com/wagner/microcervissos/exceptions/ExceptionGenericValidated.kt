package br.com.wagner.microcervissos.exceptions

import java.lang.RuntimeException

class ExceptionGenericValidated(val msg: String): RuntimeException(msg) {
}