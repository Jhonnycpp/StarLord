package br.com.jhonny.startlord.feature.home.datasource

import br.com.jhonny.startlord.feature.home.dto.GitHubRepositoryResponse

internal interface ReadGitHubDatasource {
    suspend fun getRepositories(
        page: Int,
    ): GitHubRepositoryResponse?
}

internal interface WriteGitHubDataSource : ReadGitHubDatasource {
    suspend fun save(page: Int, repositories: GitHubRepositoryResponse)
}
