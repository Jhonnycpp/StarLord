package br.com.jhonny.startlord.feature.home.datasource

import br.com.jhonny.startlord.TestScopeRule
import br.com.jhonny.startlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.startlord.feature.home.dto.GitHubRepositoryResponse
import br.com.jhonny.startlord.feature.home.dto.LicenseDTO
import br.com.jhonny.startlord.feature.home.dto.RepositoryOwnerDTO
import br.com.jhonny.startlord.feature.home.service.GitHubRepositoryService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class RemoteGitHubDatasourceTest {
    @get:Rule
    private val scope = TestScopeRule()
    private val gitHubRepositoryService = mockk<GitHubRepositoryService>()
    private val datasource = RemoteGitHubDatasource(gitHubRepositoryService)

    @Test
    fun `should successfully retrieve repositories`() = runTest {
        val response = Response.success(expectedResponse)
        coEvery {
            gitHubRepositoryService.getRepositories(
                sort = any(),
                page = any(),
            )
        } returns response

        val result = datasource.getRepositories(1)

        assertEquals(expectedResponse, result)
    }

    @Test
    fun `should fail retrieve repositories with error body`() = runTest {
        coEvery {
            gitHubRepositoryService.getRepositories(
                sort = any(),
                page = any(),
            )
        } returns Response.success(null)

        val result = datasource.getRepositories(1)

        assertNull(result)
    }

    @Test
    fun `should fail retrieve repositories with a backend error`() = runTest {
        coEvery {
            gitHubRepositoryService.getRepositories(
                sort = any(),
                page = any(),
            )
        } returns Response.error(503, mockk(relaxed = true))

        val result = datasource.getRepositories(1)

        assertNull(result)
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
