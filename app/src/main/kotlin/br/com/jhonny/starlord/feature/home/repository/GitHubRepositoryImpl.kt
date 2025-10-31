package br.com.jhonny.starlord.feature.home.repository

import android.util.Log
import br.com.jhonny.starlord.feature.home.datasource.LocalGitHubDatasource
import br.com.jhonny.starlord.feature.home.datasource.RemoteGitHubDatasource
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse
import kotlin.concurrent.atomics.ExperimentalAtomicApi

@OptIn(ExperimentalAtomicApi::class)
internal class GitHubRepositoryImpl(
    private val localDatasource: LocalGitHubDatasource,
    private val remoteDatasource: RemoteGitHubDatasource,
) : GitHubRepository {

    override suspend fun getRepositories(
        searchTerm: String,
        languages: List<String>
    ): List<GitHubRepositoryDTO> {
        val currentPage = localDatasource.getCurrentPage(searchTerm, languages)?.plus(1) ?: 1

        val currentRepositories = localDatasource.getRepositories(
            query = searchTerm,
            languages = languages,
        )

        Log.d("GitHubRepositoryImpl", "[searchQuery: $searchTerm][languages: $languages][currentPage: $currentPage][currentRepositories.size: ${currentRepositories.size}]")

        if (localDatasource.contains { page == currentPage && query == searchTerm && this.languages == languages }) {
            Log.d("GitHubRepositoryImpl", "Returning current repositories")
            return currentRepositories.flatMap { it.items }
        } else {
            Log.d("GitHubRepositoryImpl", "Returning remote repositories")
            val repository = retrieveFromRemote(
                page = currentPage,
                query = searchTerm,
                languages = languages,
            )?.items ?: emptyList()

            return currentRepositories.flatMap { it.items } + repository
        }
    }

    override suspend fun getRepository(id: Int): GitHubRepositoryDTO? {
//        for (page in 1..pageManager.page) {
//            val items = localDatasource.getRepositories(page)?.items
//            val repository = items?.find {
//                it.id == id
//            }
//
//            if (repository != null) return repository
//        }

        return null
    }

    private suspend fun retrieveFromRemote(
        page: Int,
        query: String,
        languages: List<String>,
    ): GitHubRepositoryResponse? = remoteDatasource.getRepositories(
        page = page,
        query = query,
        languages = languages,
    )?.also {
        localDatasource.save(
            page = page,
            query = query,
            languages = languages,
            repositories = it,
        )
    }
}
