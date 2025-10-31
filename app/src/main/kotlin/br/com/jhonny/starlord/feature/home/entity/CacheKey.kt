package br.com.jhonny.starlord.feature.home.entity

internal data class CacheKey(
    val page: Int,
    val query: String,
    val languages: List<String>,
)
