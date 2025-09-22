package br.com.jhonny.starlord.feature.home.datasource

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse

internal interface ReadGitHubDatasource {
    suspend fun getRepositories(
        page: Int,
    ): GitHubRepositoryResponse?
}

internal interface WriteGitHubDataSource : ReadGitHubDatasource {
    suspend fun save(page: Int, repositories: GitHubRepositoryResponse)
}
