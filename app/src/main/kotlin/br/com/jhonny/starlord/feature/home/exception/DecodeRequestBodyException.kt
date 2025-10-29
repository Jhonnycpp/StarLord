package br.com.jhonny.starlord.feature.home.exception

/**
 * Exception thrown when the request body fails to be decoded.
 *
 * This typically occurs when the incoming request data is malformed or not in the expected format.
 */
internal class DecodeRequestBodyException : Throwable("Fail to decode request body.")
