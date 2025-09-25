package br.com.jhonny.starlord.feature.home.repository

import br.com.jhonny.starlord.feature.home.datasource.PageDatasource
import br.com.jhonny.starlord.feature.home.datasource.ReadGitHubDatasource
import br.com.jhonny.starlord.feature.home.datasource.WriteGitHubDataSource
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse
import kotlin.concurrent.atomics.ExperimentalAtomicApi

@OptIn(ExperimentalAtomicApi::class)
internal class GitHubRepositoryImpl(
    private val localDatasource: WriteGitHubDataSource,
    private val remoteDatasource: ReadGitHubDatasource,
    private val pageManager: PageDatasource,
) : GitHubRepository {

    override suspend fun getRepositories(): List<GitHubRepositoryDTO> {
        val currentRepositories = (1 until pageManager.page).map { page ->
            localDatasource.getRepositories(page)
        }
        val localRepositories = localDatasource.getRepositories(pageManager.page)
        val remoteRepositories = localRepositories ?: retrieveFromRemote(pageManager.page)

        val repositories = currentRepositories + (localRepositories ?: remoteRepositories)

        return repositories.flatMap {
            it?.items ?: emptyList()
        }
    }

    override suspend fun getRepository(id: Int): GitHubRepositoryDTO? {
        for (page in 1..pageManager.page) {
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
        pageManager.increment()
    }
}
