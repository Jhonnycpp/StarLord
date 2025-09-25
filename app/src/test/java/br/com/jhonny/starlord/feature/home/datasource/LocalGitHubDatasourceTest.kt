package br.com.jhonny.starlord.feature.home.datasource

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse
import br.com.jhonny.starlord.feature.home.dto.LicenseDTO
import br.com.jhonny.starlord.feature.home.dto.RepositoryOwnerDTO
import br.com.jhonny.starlord.rule.TestCoroutineScopeRule
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.ConcurrentHashMap

class LocalGitHubDatasourceTest {
    @get:Rule
    private val scope = TestCoroutineScopeRule()

    @Test
    fun `should successfully retrieve repositories`() = runTest {
        val datasource = LocalGitHubDatasource(
            ConcurrentHashMap(mapOf(1 to expectedResponse))
        )

        val response = datasource.getRepositories(1)

        assertEquals(expectedResponse, response)
    }

    @Test
    fun `should fail retrieve repositories`() = runTest {
        val datasource = LocalGitHubDatasource()

        val result = datasource.getRepositories(1)

        assertNull(result)
    }

    @Test
    fun `should successfully save repositories`() = runTest {
        val datasource = LocalGitHubDatasource()

        assertNull("The value should not be retrieved yet.", datasource.getRepositories(1))

        datasource.save(1, expectedResponse)

        val result = datasource.getRepositories(1)

        assertEquals(expectedResponse, result)
    }

    @Test
    fun `should fail save repositories`() = runTest {
        val cache = mockk<ConcurrentHashMap<Int, GitHubRepositoryResponse>> {
            every { put(any(), any()) } throws NullPointerException()
        }
        val datasource = LocalGitHubDatasource(cache)

        datasource.save(1, expectedResponse)

        coVerify { cache[1] = expectedResponse }
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
    }
}
