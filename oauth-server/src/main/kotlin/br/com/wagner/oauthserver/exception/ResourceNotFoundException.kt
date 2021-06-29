package br.com.wagner.oauthserver.exception

class ResourceNotFoundException(val msg: String): RuntimeException(msg) {
}