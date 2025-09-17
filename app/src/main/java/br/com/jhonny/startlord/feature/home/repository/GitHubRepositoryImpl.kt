package br.com.jhonny.startlord.feature.home.repository

import br.com.jhonny.startlord.feature.home.datasource.ReadGitHubDatasource
import br.com.jhonny.startlord.feature.home.datasource.WriteGitHubDataSource
import br.com.jhonny.startlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.startlord.feature.home.dto.GitHubRepositoryResponse

internal class GitHubRepositoryImpl(
    private val localDatasource: WriteGitHubDataSource,
    private val remoteDatasource: ReadGitHubDatasource,
) : GitHubRepository {

    override suspend fun getRepositories(
        page: Int,
    ): List<GitHubRepositoryDTO> {
        val repositoryResponse = localDatasource.getRepositories(page) ?: retrieveFromRemote(page)
        return repositoryResponse?.items ?: emptyList()
    }

    private suspend fun retrieveFromRemote(
        page: Int,
    ): GitHubRepositoryResponse? = remoteDatasource.getRepositories(page)?.also {
        localDatasource.save(page, it)
    }
}
