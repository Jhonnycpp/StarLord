package br.com.jhonny.starlord.feature.home.exception

/**
 * Exception thrown when a value is expected to be present in the cache but is not found.
 */
internal class CacheValueMissingException : Throwable("Missing value in cache")
