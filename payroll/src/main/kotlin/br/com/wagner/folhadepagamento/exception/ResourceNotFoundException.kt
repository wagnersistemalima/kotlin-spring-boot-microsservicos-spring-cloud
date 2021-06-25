package br.com.wagner.folhadepagamento.exception

import java.lang.RuntimeException

class ResourceNotFoundException(val msg: String): RuntimeException(msg) {
}