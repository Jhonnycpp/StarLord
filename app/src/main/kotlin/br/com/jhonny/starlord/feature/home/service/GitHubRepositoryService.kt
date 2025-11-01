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
     * Fetches a paginated list of GitHub repositories based on a search query.
     *
     * This function queries the GitHub API's `search/repositories` endpoint.
     *
     * @param query The search keywords, as well as any qualifiers.
     * @param sort The sort field. Can be one of: stars, forks, help-wanted-issues, updated. Default: best match.
     * @param page The page number of the results to retrieve.
     * @param order The sort order. Can be `desc` or `asc`. Default: `desc`.
     * @param perPage The number of results to return per page.
     * @return A Retrofit [Response] wrapping a [GitHubRepositoryResponse] on success.
     */
    @GET("search/repositories")
    public suspend fun getRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("order") order: String,
        @Query("per_page") perPage: Int,
    ): Response<GitHubRepositoryResponse>
}
