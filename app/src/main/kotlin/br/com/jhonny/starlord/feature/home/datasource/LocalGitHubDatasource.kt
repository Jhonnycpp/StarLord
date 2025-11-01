package br.com.jhonny.starlord.feature.home.datasource

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse

/**
 * Defines the contract for local data source operations related to GitHub repositories.
 *
 * This interface is responsible for managing the caching of GitHub repository data,
 * including saving, retrieving, and managing pagination state.
 */
internal interface LocalGitHubDatasource {
    suspend fun getRepositories(
        query: String,
        languages: List<String>,
    ): List<GitHubRepositoryResponse>

    /**
     * Retrieves a single GitHub repository by its unique identifier from the local data source.
     *
     * @param id The unique ID of the repository to retrieve.
     * @return The [GitHubRepositoryDTO] if found, otherwise null.
     */
    suspend fun getRepository(id: Int): GitHubRepositoryDTO?

    /**
     * Saves a response containing a list of GitHub repositories to the local data source.
     * This operation is associated with a specific query, set of languages, and page number
     * to enable paginated caching.
     *
     * @param page The page number for which the repositories are being saved.
     * @param query The search query used to fetch these repositories.
     * @param languages The list of programming languages used to filter the repositories.
     * @param repositories The [GitHubRepositoryResponse] object containing the list of repositories to save.
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
