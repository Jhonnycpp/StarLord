package br.com.jhonny.starlord.feature.home.datasource

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse
import br.com.jhonny.starlord.feature.home.entity.CacheKey
import java.util.concurrent.ConcurrentHashMap

internal class CacheGitHubDatasource(
    private val cache: ConcurrentHashMap<CacheKey, GitHubRepositoryResponse> = ConcurrentHashMap(),
) : LocalGitHubDatasource {
    override suspend fun getRepositories(
        query: String,
        languages: List<String>,
    ): List<GitHubRepositoryResponse> = cache.filter { (key, _) -> key.query == query && key.languages == languages }.values.toList()

    override suspend fun getRepository(id: Int): GitHubRepositoryDTO? = cache.values.flatMap { it.items }.firstOrNull { it.id == id }

    override suspend fun save(
        page: Int,
        query: String,
        languages: List<String>,
        repositories: GitHubRepositoryResponse
    ) {
        val key = CacheKey(
            page = page,
            query = query,
            languages = languages,
        )
        cache[key] = repositories
    }

    override suspend fun getCurrentPage(query: String, languages: List<String>): Int? {
        val keys = cache.filterKeys { it.query == query && it.languages == languages }.keys

        val currentPage = keys.maxByOrNull { it.page }?.page

        return currentPage
    }
}
