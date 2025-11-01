package br.com.jhonny.starlord.feature.home.repository

import android.util.Log
import br.com.jhonny.starlord.feature.home.datasource.LocalGitHubDatasource
import br.com.jhonny.starlord.feature.home.datasource.RemoteGitHubDatasource
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse

internal class GitHubRepositoryImpl(
    private val localDatasource: LocalGitHubDatasource,
    private val remoteDatasource: RemoteGitHubDatasource,
) : GitHubRepository {

    override suspend fun getRepositories(
        searchTerm: String,
        languages: List<String>
    ): List<GitHubRepositoryDTO> {
        val currentPage = localDatasource.getCurrentPage(searchTerm, languages) ?: 0
        val nextPage = currentPage + 1

        Log.d("GitHubRepositoryImpl", "[searchQuery: $searchTerm][languages: $languages][currentPage: $currentPage][fetchingPage: $nextPage]")

        val cachedRepositories = localDatasource.getRepositories(
            query = searchTerm,
            languages = languages,
        )

        val remoteRepositories = fetchAndSaveRepositories(
            page = nextPage,
            query = searchTerm,
            languages = languages,
        )?.items ?: emptyList()

        Log.d("GitHubRepositoryImpl", "Returning a total of ${cachedRepositories.size} pages from cache.")

        return cachedRepositories.flatMap { it.items } + remoteRepositories
    }

    override suspend fun getRepository(id: Int): GitHubRepositoryDTO? = localDatasource.getRepository(id)

    private suspend fun fetchAndSaveRepositories(
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
