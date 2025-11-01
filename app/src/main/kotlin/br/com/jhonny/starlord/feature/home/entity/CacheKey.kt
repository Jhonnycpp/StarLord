package br.com.jhonny.starlord.feature.home.entity

/**
 * Represents a unique key for caching repository search results.
 * This key is composed of the search parameters used to fetch the data.
 *
 * @property page The page number of the search results.
 * @property query The search term or query string used.
 * @property languages The list of programming languages to filter the results by.
 */
internal data class CacheKey(
    val page: Int,
    val query: String,
    val languages: List<String>,
)
