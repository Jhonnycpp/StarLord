package br.com.jhonny.starlord.feature.home.repository

import br.com.jhonny.starlord.feature.home.datasource.LocalGitHubDatasource
import br.com.jhonny.starlord.feature.home.datasource.RemoteGitHubDatasource
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse
import br.com.jhonny.starlord.feature.home.dto.LicenseDTO
import br.com.jhonny.starlord.feature.home.dto.RepositoryOwnerDTO
import br.com.jhonny.starlord.rule.TestCoroutineScopeRule
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

internal class GitHubRepositoryImplTest {
    @get:Rule
    private val scope = TestCoroutineScopeRule()
    private val localDatasource = mockk<LocalGitHubDatasource>()
    private val remoteDatasource = mockk<RemoteGitHubDatasource>()
    private val repository = GitHubRepositoryImpl(
        localDatasource = localDatasource,
        remoteDatasource = remoteDatasource,
    )

    @Test
    fun `should successfully retrieve repositories from local datasource`() = runTest {
        coEvery { localDatasource.contains(any()) } returns true
        coEvery { localDatasource.getCurrentPage(any(), any()) } returns 1
        coEvery { localDatasource.getRepositories(any(), any()) } returns listOf(expectedResponse)

        val result = repository.getRepositories("query", listOf("language"))

        assertEquals(expectedResponse.items, result)
        coVerify(exactly = 1) { localDatasource.getRepositories("query", listOf("language")) }
        coVerify(exactly = 0) { remoteDatasource.getRepositories(any(), any(), any()) }
    }

    @Test
    fun `should successfully retrieve repositories from remote datasource`() = runTest {
        coEvery { localDatasource.contains(any()) } returns false
        coEvery { localDatasource.getCurrentPage(any(), any()) } returns 1
        coEvery { localDatasource.getRepositories(any(), any()) } returns emptyList()
        coJustRun { localDatasource.save(any(), any(), any(), any()) }
        coEvery { remoteDatasource.getRepositories(any(), any(), any()) } returns expectedResponse

        val result = repository.getRepositories("query", listOf("language"))

        assertEquals(expectedResponse.items, result)
        coVerify(exactly = 1) { remoteDatasource.getRepositories(2, "query", listOf("language")) }
        coVerify(exactly = 1) { localDatasource.save(2, "query", listOf("language"), expectedResponse) }
    }

    @Test
    fun `should successfully retrieve repositories with multiple pages`() = runTest {
        coEvery { localDatasource.contains(any()) } returns false
        coEvery { localDatasource.getCurrentPage(any(), any()) } returns 1
        coEvery { localDatasource.getRepositories(any(), any()) } returns listOf(expectedResponse)
        coJustRun { localDatasource.save(any(), any(), any(), any()) }
        coEvery { remoteDatasource.getRepositories(any(), any(), any()) } returns expectedResponse

        val result = repository.getRepositories("query", listOf("language"))

        assertTrue(result.size == 2)
        coVerify(exactly = 1) { localDatasource.getRepositories("query", listOf("language")) }
        coVerify(exactly = 1) { remoteDatasource.getRepositories(2, "query", listOf("language")) }
        coVerify(exactly = 1) { localDatasource.save(2, "query", listOf("language"), expectedResponse) }
    }

    @Test
    fun `should fail retrieve repositories`() = runTest {
        coEvery { localDatasource.getCurrentPage(any(), any()) } returns null
        coEvery { localDatasource.getRepositories(any(), any()) } returns emptyList()
        coEvery { localDatasource.contains(any()) } returns false
        coEvery { remoteDatasource.getRepositories(any(), any(), any()) } returns null

        val result = repository.getRepositories("query", listOf("language"))

        assertTrue(result.isEmpty())
        coVerify(exactly = 1) { remoteDatasource.getRepositories(1, "query", listOf("language")) }
        coVerify(exactly = 0) { localDatasource.save(any(), any(), any(), any()) }
    }

    @Test
    fun `should successfully retrieve repository`() = runTest {
        coEvery { localDatasource.getRepository(any()) } returns repositoryDTO

        val result = repository.getRepository(1)

        assertEquals(repositoryDTO, result)
        coVerify(exactly = 1) { localDatasource.getRepository(id = 1) }
    }

    @Test
    fun `should fail retrieve repository with empty local datasource`() = runTest {
        coEvery { localDatasource.getRepository(any()) } returns null

        val result = repository.getRepository(1)

        assertNull(result)
        coVerify(exactly = 1) { localDatasource.getRepository(id = 1) }
    }

    private companion object {
        val repositoryDTO = GitHubRepositoryDTO(
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
        val expectedResponse = GitHubRepositoryResponse(
            items = listOf(
                repositoryDTO,
            ),
            totalCount = 1,
        )
    }
}
