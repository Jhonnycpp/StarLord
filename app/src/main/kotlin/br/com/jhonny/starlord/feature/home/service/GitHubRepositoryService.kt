package br.com.jhonny.starlord.feature.home.service

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service interface for interacting with the GitHub API to retrieve repository information.
 */
public interface GitHubRepositoryService {
    /**
     * Retrieves a list of GitHub repositories.
     *
     * @param sort The sort field. Can be one of: stars, forks, help-wanted-issues, updated.
     * @param page The page number of the results to retrieve.
     * @return A [Response] object containing a [GitHubRepositoryResponse] if successful, or an error otherwise.
     */
    @GET("search/repositories")
    public suspend fun getRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
    ): Response<GitHubRepositoryResponse>
}
