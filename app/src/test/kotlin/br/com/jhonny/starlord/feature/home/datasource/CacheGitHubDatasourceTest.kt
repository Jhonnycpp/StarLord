package br.com.jhonny.starlord.feature.home.datasource

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse
import br.com.jhonny.starlord.feature.home.dto.LicenseDTO
import br.com.jhonny.starlord.feature.home.dto.RepositoryOwnerDTO
import br.com.jhonny.starlord.feature.home.entity.CacheKey
import br.com.jhonny.starlord.rule.TestCoroutineScopeRule
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
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
    fun `should fail retrieve repositories`() = runTest {
        val datasource = CacheGitHubDatasource()

        val result = datasource.getRepositories("query", listOf("language"))

        assertEquals(emptyList<GitHubRepositoryResponse>(), result)
    }

    @Test
    fun `should successfully save repositories`() = runTest {
        val datasource = CacheGitHubDatasource()

        assertEquals("The value should not be retrieved yet.", emptyList<GitHubRepositoryResponse>(), datasource.getRepositories("query", listOf("language")))

        datasource.save(1, "query", listOf("language"), expectedResponse)

        val result = datasource.getRepositories("query", listOf("language"))

        assertEquals(listOf(expectedResponse), result)
    }

    @Test
    fun `should fail save repositories`() = runTest {
        val cache = mockk<ConcurrentHashMap<CacheKey, GitHubRepositoryResponse>> {
            every { put(any(), any()) } throws NullPointerException()
        }
        val datasource = CacheGitHubDatasource(cache)

        datasource.save(1, "query", listOf("language"),expectedResponse)

        coVerify { cache[cacheKey] = expectedResponse }
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
