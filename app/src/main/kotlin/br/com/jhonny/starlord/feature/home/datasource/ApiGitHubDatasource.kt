package br.com.jhonny.starlord.feature.home.datasource

import android.util.Log
import br.com.jhonny.starlord.extension.Space
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse
import br.com.jhonny.starlord.feature.home.exception.DecodeRequestBodyException
import br.com.jhonny.starlord.feature.home.exception.FailGitHubRequestException
import br.com.jhonny.starlord.feature.home.service.GitHubRepositoryService

internal class ApiGitHubDatasource(
    private val service: GitHubRepositoryService,
) : RemoteGitHubDatasource {

    override suspend fun getRepositories(
        page: Int,
        query: String,
        languages: List<String>,
    ): GitHubRepositoryResponse? = runCatching {
        Log.d("RemoteGitHubDatasource", "Retrieve the value from remote to page.")
        val response = service.getRepositories(
            sort = DEFAULT_REPOSITORY_SORT_VALUE,
            query = buildQuery(
                query = query,
                languages = languages,
            ),
            page = page,
            order = DEFAULT_ORDER,
            perPage = DEFAULT_PER_PAGE,
        )

        if (response.isSuccessful) {
            response.body() ?: throw DecodeRequestBodyException()
        } else {
            val errorBody = response.errorBody()?.string()
            throw FailGitHubRequestException(response.code(), errorBody)
        }
    }.onFailure {
        Log.d("RemoteGitHubDatasource", "Fail retrieve the value from remote to page $page.", it)
    }.getOrNull()

    private fun buildQuery(
        query: String,
        languages: List<String>,
    ) = buildString {
        if (query.isBlank() && languages.isEmpty()) append(DEFAULT_SEARCH_QUERY)
        if (query.isNotBlank()) append("$REPOSITORY_SEARCH_KEY $query")
        if (query.isNotBlank() && languages.isNotEmpty()) append(String.Space)
        append(languages.joinToString(separator = String.Space) { "$REPOSITORY_LANGUAGE_KEY$it" })
    }.also {
        Log.d("RemoteGitHubDatasource", "[Query: $it]")
    }

    private companion object {
        const val DEFAULT_REPOSITORY_SORT_VALUE = "stars"
        const val REPOSITORY_LANGUAGE_KEY = "language:"
        const val REPOSITORY_SEARCH_KEY = "in:name,description,readme"
        const val DEFAULT_SEARCH_QUERY = "is:public"
        const val DEFAULT_ORDER = "desc"
        const val DEFAULT_PER_PAGE = 100
    }
}
