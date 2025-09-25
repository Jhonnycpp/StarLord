package br.com.jhonny.starlord.feature.home.repository

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO

/**
 * Interface for accessing GitHub repository data.
 */
public interface GitHubRepository {
    /**
     * Retrieves a list of GitHub repositories.
     *
     * This function fetches a list of GitHub repositories.
     * The repositories are represented as [GitHubRepositoryDTO] objects.
     *
     * @return A list of [GitHubRepositoryDTO] objects, or an empty list if no repositories are found.
     */
    public suspend fun getRepositories(): List<GitHubRepositoryDTO>
    /**
     * Retrieves a GitHub repository by its ID.
     *
     * @param id The ID of the repository to retrieve.
     * @return The [GitHubRepositoryDTO] if found, or null if not found.
     */
    public suspend fun getRepository(id: Int): GitHubRepositoryDTO?
}
