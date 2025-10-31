package br.com.jhonny.starlord.feature.home.datasource

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse

/**
 * Datasource responsible for reading data from GitHub.
 */
internal interface RemoteGitHubDatasource {
    suspend fun getRepositories(
        page: Int,
        query: String,
        languages: List<String>,
    ): GitHubRepositoryResponse?
}
