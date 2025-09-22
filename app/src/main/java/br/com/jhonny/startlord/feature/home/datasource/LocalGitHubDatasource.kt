package br.com.jhonny.startlord.feature.home.datasource

import android.util.Log
import br.com.jhonny.startlord.feature.home.dto.GitHubRepositoryResponse
import br.com.jhonny.startlord.feature.home.exception.CacheValueMissingException
import java.util.concurrent.ConcurrentHashMap

internal class LocalGitHubDatasource(
    private val cache: ConcurrentHashMap<Int, GitHubRepositoryResponse> = ConcurrentHashMap(),
) : WriteGitHubDataSource {
    override suspend fun getRepositories(
        page: Int,
    ): GitHubRepositoryResponse? = runCatching {
        cache[page] ?: throw CacheValueMissingException()
    }.onFailure {
        Log.d("LocalGitHubDatasource", "Fail retrieve the value from cache to page [$page].", it)
    }.getOrNull()

    override suspend fun save(page: Int, repositories: GitHubRepositoryResponse) {
        runCatching {
            cache[page] = repositories
        }.onFailure {
            Log.d("LocalGitHubDatasource", "Fail save the value to cache to page [$page].", it)
        }
    }
}
