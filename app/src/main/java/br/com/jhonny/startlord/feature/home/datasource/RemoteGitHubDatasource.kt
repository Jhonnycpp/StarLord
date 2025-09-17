package br.com.jhonny.startlord.feature.home.datasource

import android.util.Log
import br.com.jhonny.startlord.feature.home.dto.GitHubRepositoryResponse
import br.com.jhonny.startlord.feature.home.exception.DecodeRequestBodyException
import br.com.jhonny.startlord.feature.home.exception.FailGitHubRequestException
import br.com.jhonny.startlord.feature.home.service.GitHubRepositoryService

internal class RemoteGitHubDatasource(
    private val service: GitHubRepositoryService,
) : ReadGitHubDatasource {

    override suspend fun getRepositories(
        page: Int,
    ): GitHubRepositoryResponse? = runCatching {
        Log.d("RemoteGitHubDatasource", "Retrieve the value from remote to page $page.")
        val response = service.getRepositories(
            sort = DEFAULT_REPOSITORY_SORT_KEY,
            page = page,
        )

        if (response.isSuccessful) {
            response.body() ?: throw DecodeRequestBodyException()
        } else {
            val errorBody = response.errorBody()?.string()
            throw FailGitHubRequestException(errorBody)
        }
    }.onFailure {
        Log.d("RemoteGitHubDatasource", "Fail retrieve the value from remote to page $page.", it)
    }.getOrNull()

    private companion object {
        const val DEFAULT_REPOSITORY_SORT_KEY = "stars"
    }
}
