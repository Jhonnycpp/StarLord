package br.com.jhonny.starlord.feature.home.repository

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO

/**
 * Interface for accessing GitHub repository data.
 */
public interface GitHubRepository {
    /**
     * Retrieves a list of GitHub repositories filtered by a query and languages.
     *
     * This function fetches a list of GitHub repositories based on a search query
     * and a specified list of programming languages. The results are returned
     * as a list of [GitHubRepositoryDTO] objects.
     *
     * @param searchTerm The search term to filter repositories by (e.g., "starlord", "android").
     * @param languages A list of programming languages to filter the search results (e.g., "kotlin", "java").
     * @return A list of [GitHubRepositoryDTO] objects matching the criteria, or an empty list if none are found.
     */
    public suspend fun getRepositories(searchTerm: String, languages: List<String>): List<GitHubRepositoryDTO>
    /**
     * Retrieves a GitHub repository by its ID.
     *
     * @param id The ID of the repository to retrieve.
     * @return The [GitHubRepositoryDTO] if found, or null if not found.
     */
    public suspend fun getRepository(id: Int): GitHubRepositoryDTO?
}
