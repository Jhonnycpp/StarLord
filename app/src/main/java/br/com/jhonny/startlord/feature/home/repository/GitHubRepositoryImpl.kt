package br.com.jhonny.startlord.feature.home.repository

import br.com.jhonny.startlord.feature.home.datasource.ReadGitHubDatasource
import br.com.jhonny.startlord.feature.home.datasource.WriteGitHubDataSource
import br.com.jhonny.startlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.startlord.feature.home.dto.GitHubRepositoryResponse

internal class GitHubRepositoryImpl(
    private val localDatasource: WriteGitHubDataSource,
    private val remoteDatasource: ReadGitHubDatasource,
) : GitHubRepository {

    override suspend fun getRepositories(): List<GitHubRepositoryDTO> {
        val currentRepositories = (1 until currentPage).map { page ->
            localDatasource.getRepositories(page)
        }
        val localRepositories = localDatasource.getRepositories(currentPage)
        val remoteRepositories = localRepositories ?: retrieveFromRemote(currentPage)

        val repositories = currentRepositories + (localRepositories ?: remoteRepositories)

        return repositories.flatMap {
            it?.items ?: emptyList()
        }
    }

    override suspend fun getRepository(id: Int): GitHubRepositoryDTO? {
        for (page in 1..currentPage) {
            val items = localDatasource.getRepositories(page)?.items
            val repository = items?.find {
                it.id == id
            }

            if (repository != null) return repository
        }

        return null
    }

    private suspend fun retrieveFromRemote(
        page: Int,
    ): GitHubRepositoryResponse? = remoteDatasource.getRepositories(page)?.also {
        localDatasource.save(page, it)
        currentPage++
    }

    private companion object {
        private var currentPage = 1
    }
}
