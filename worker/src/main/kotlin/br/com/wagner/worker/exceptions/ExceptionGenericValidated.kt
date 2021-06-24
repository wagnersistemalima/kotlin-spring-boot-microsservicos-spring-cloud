package br.com.wagner.worker.exceptions

import java.lang.RuntimeException

class ExceptionGenericValidated(val msg: String): RuntimeException(msg) {
}