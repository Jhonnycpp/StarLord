package br.com.jhonny.starlord.feature.home.datasource

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse

/**
 * Datasource responsible for fetching repository data from the GitHub API.
 */
internal interface RemoteGitHubDatasource {
    /**
     * Fetches a list of repositories from the GitHub API based on a search query.
     *
     * @param page The page number for pagination.
     * @param query The search query string.
     * @param languages A list of programming languages to filter the search results.
     * @return A [GitHubRepositoryResponse] containing the list of found repositories, or null if the request fails.
     */
    suspend fun getRepositories(
        page: Int,
        query: String,
        languages: List<String>,
    ): GitHubRepositoryResponse?
}
