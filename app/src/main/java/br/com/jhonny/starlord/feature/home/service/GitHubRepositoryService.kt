package br.com.jhonny.starlord.feature.home.service

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

public interface GitHubRepositoryService {
    @GET("search/repositories?q=language:kotlin")
    public suspend fun getRepositories(
        @Query("sort") sort: String,
        @Query("page") page: Int,
    ): Response<GitHubRepositoryResponse>
}
