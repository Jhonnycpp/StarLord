package br.com.jhonny.starlord.feature.home.datasource

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse
import br.com.jhonny.starlord.feature.home.dto.LicenseDTO
import br.com.jhonny.starlord.feature.home.dto.RepositoryOwnerDTO
import br.com.jhonny.starlord.feature.home.entity.CacheKey
import br.com.jhonny.starlord.rule.TestCoroutineScopeRule
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.ConcurrentHashMap

class CacheGitHubDatasourceTest {
    @get:Rule
    private val scope = TestCoroutineScopeRule()

    @Test
    fun `should successfully retrieve repositories`() = runTest {
        val datasource = CacheGitHubDatasource(
            ConcurrentHashMap(mapOf(cacheKey to expectedResponse))
        )

        val response = datasource.getRepositories("query", listOf("language"))

        assertEquals(listOf(expectedResponse), response)
    }

    @Test
    fun `should return empty list when cache is empty`() = runTest {
        val datasource = CacheGitHubDatasource()

        val result = datasource.getRepositories("query", listOf("language"))

        assertEquals(emptyList<GitHubRepositoryResponse>(), result)
    }

    @Test
    fun `should retrieve only matching repositories from a populated cache`() = runTest {
        val anotherCacheKey = CacheKey(1, "another-query", listOf("another-language"))
        val anotherResponse = GitHubRepositoryResponse(items = emptyList(), totalCount = 0)
        val cache = ConcurrentHashMap(mapOf(
            cacheKey to expectedResponse,
            anotherCacheKey to anotherResponse
        ))
        val datasource = CacheGitHubDatasource(cache)

        val result = datasource.getRepositories("query", listOf("language"))

        assertEquals(listOf(expectedResponse), result)
    }

    @Test
    fun `should retrieve multiple matching repositories`() = runTest {
        val cacheKeyPage2 = CacheKey(2, "query", listOf("language"))
        val responsePage2 = GitHubRepositoryResponse(items = listOf(mockk()), totalCount = 2)
        val anotherCacheKey = CacheKey(1, "another-query", listOf("another-language"))
        val anotherResponse = GitHubRepositoryResponse(items = emptyList(), totalCount = 0)

        val cache = ConcurrentHashMap(mapOf(
            cacheKey to expectedResponse,
            cacheKeyPage2 to responsePage2,
            anotherCacheKey to anotherResponse
        ))
        val datasource = CacheGitHubDatasource(cache)

        val result = datasource.getRepositories("query", listOf("language"))

        assertEquals(listOf(expectedResponse, responsePage2), result)
    }

    @Test
    fun `should successfully save repositories`() = runTest {
        val datasource = CacheGitHubDatasource()

        assertEquals(
            "The value should not be retrieved yet.",
            emptyList<GitHubRepositoryResponse>(),
            datasource.getRepositories("query", listOf("language"))
        )

        datasource.save(1, "query", listOf("language"), expectedResponse)

        val result = datasource.getRepositories("query", listOf("language"))

        assertEquals(listOf(expectedResponse), result)
    }

    @Test
    fun `should overwrite existing entry when saving with same key`() = runTest {
        val datasource = CacheGitHubDatasource(ConcurrentHashMap(mapOf(cacheKey to expectedResponse)))
        val updatedResponse = expectedResponse.copy(totalCount = 99)

        datasource.save(1, "query", listOf("language"), updatedResponse)

        val result = datasource.getRepositories("query", listOf("language"))
        assertEquals(1, result.size)
        assertEquals(updatedResponse, result[0])
    }

    @Test
    fun `should attempt to save to cache`() = runTest {
        val cache = mockk<ConcurrentHashMap<CacheKey, GitHubRepositoryResponse>>(relaxed = true)
        val datasource = CacheGitHubDatasource(cache)

        datasource.save(1, "query", listOf("language"), expectedResponse)

        verify { cache[cacheKey] = expectedResponse }
    }

    @Test
    fun `getRepository should return repository when found`() = runTest {
        val datasource = CacheGitHubDatasource(ConcurrentHashMap(mapOf(cacheKey to expectedResponse)))
        val result = datasource.getRepository(1)
        assertEquals(expectedResponse.items.first(), result)
    }

    @Test
    fun `getRepository should return null when not found`() = runTest {
        val datasource = CacheGitHubDatasource(ConcurrentHashMap(mapOf(cacheKey to expectedResponse)))
        val result = datasource.getRepository(2)
        assertNull(result)
    }

    @Test
    fun `getRepository should return null for empty cache`() = runTest {
        val datasource = CacheGitHubDatasource()
        val result = datasource.getRepository(1)
        assertNull(result)
    }

    @Test
    fun `getCurrentPage should return correct page`() = runTest {
        val cacheKeyPage2 = CacheKey(2, "query", listOf("language"))
        val responsePage2 = GitHubRepositoryResponse(items = listOf(mockk()), totalCount = 2)
        val cache = ConcurrentHashMap(mapOf(
            cacheKey to expectedResponse,
            cacheKeyPage2 to responsePage2
        ))
        val datasource = CacheGitHubDatasource(cache)

        val result = datasource.getCurrentPage("query", listOf("language"))

        assertEquals(2, result)
    }

    @Test
    fun `getCurrentPage should return null for non-existent query`() = runTest {
        val datasource = CacheGitHubDatasource(ConcurrentHashMap(mapOf(cacheKey to expectedResponse)))
        val result = datasource.getCurrentPage("non-existent", listOf("language"))
        assertNull(result)
    }

    @Test
    fun `getCurrentPage should return null for empty cache`() = runTest {
        val datasource = CacheGitHubDatasource()
        val result = datasource.getCurrentPage("query", listOf("language"))
        assertNull(result)
    }

    @Test
    fun `contains should return true when condition met`() = runTest {
        val datasource = CacheGitHubDatasource(ConcurrentHashMap(mapOf(cacheKey to expectedResponse)))
        val result = datasource.contains { page == 1 && query == "query" }
        assertTrue(result)
    }

    @Test
    fun `contains should return false when condition not met`() = runTest {
        val datasource = CacheGitHubDatasource(ConcurrentHashMap(mapOf(cacheKey to expectedResponse)))
        val result = datasource.contains { page == 2 }
        assertFalse(result)
    }

    @Test
    fun `contains should return false for empty cache`() = runTest {
        val datasource = CacheGitHubDatasource()
        val result = datasource.contains { true }
        assertFalse(result)
    }

    private companion object {
        val expectedResponse = GitHubRepositoryResponse(
            items = listOf(
                GitHubRepositoryDTO(
                    id = 1,
                    name = "name",
                    description = "description",
                    owner = RepositoryOwnerDTO(
                        author = "author",
                        avatar = "avatar",
                    ),
                    forksCount = 1,
                    language = "language",
                    license = LicenseDTO("Apache License 2.0"),
                    createdAt = "createdAt",
                    updatedAt = "updatedAt",
                    pushedAt = "pushedAt",
                    watchersCount = 1,
                    openIssuesCount = 1,
                    starCount = 1,
                )
            ),
            totalCount = 1,
        )

        val cacheKey = CacheKey(
            page = 1,
            query = "query",
            languages = listOf("language"),
        )
    }
}
