package br.com.jhonny.starlord.feature.home.datasource

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse

/**
 * Datasource responsible for reading data from GitHub.
 */
internal interface ReadGitHubDatasource {
    /**
     * Retrieves a list of GitHub repositories.
     *
     * @param page The page number to fetch.
     * @return A [GitHubRepositoryResponse] object containing the list of repositories,
     * or null if the request fails or no data is found.
     */
    suspend fun getRepositories(
        page: Int,
    ): GitHubRepositoryResponse?
}

/**
 * Interface responsible for defining write operations related to GitHub data.
 * It extends [ReadGitHubDatasource] to also include read operations.
 */
internal interface WriteGitHubDataSource : ReadGitHubDatasource {
    /**
     * Saves a [GitHubRepositoryResponse] to the local data source for a given page.
     *
     * @param page The page number to save the repositories for.
     * @param repositories The [GitHubRepositoryResponse] to save.
     */
    suspend fun save(page: Int, repositories: GitHubRepositoryResponse)
}
