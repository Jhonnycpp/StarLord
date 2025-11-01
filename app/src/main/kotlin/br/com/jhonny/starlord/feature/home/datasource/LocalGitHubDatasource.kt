package br.com.jhonny.starlord.feature.home.datasource

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse

/**
 * Interface responsible for defining write operations related to GitHub data.
 * It extends [RemoteGitHubDatasource] to also include read operations.
 */
internal interface LocalGitHubDatasource {
    suspend fun getRepositories(
        query: String,
        languages: List<String>,
    ): List<GitHubRepositoryResponse>

    suspend fun getRepository(id: Int): GitHubRepositoryDTO?

    /**
     * Saves a [GitHubRepositoryResponse] to the local data source for a given page.
     *
     * @param page The page number to save the repositories for.
     * @param repositories The [GitHubRepositoryResponse] to save.
     */
    suspend fun save(
        page: Int,
        query: String,
        languages: List<String>,
        repositories: GitHubRepositoryResponse
    )

    /**
     * Retrieves the current page number for a given query and set of languages.
     *
     * @param query The search query used to find repositories.
     * @param languages The list of programming languages to filter the repositories by.
     * @return The current page number as an [Int].
     */
    suspend fun getCurrentPage(
        query: String,
        languages: List<String>,
    ): Int?
}
