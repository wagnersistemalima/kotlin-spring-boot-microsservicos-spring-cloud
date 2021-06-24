package br.com.wagner.worker.exceptions

class ResourceNotFoundException(val msg: String): RuntimeException(msg) {
}