package br.com.jhonny.starlord.feature.home.repository

import br.com.jhonny.starlord.feature.home.datasource.PageDatasource
import br.com.jhonny.starlord.feature.home.datasource.ReadGitHubDatasource
import br.com.jhonny.starlord.feature.home.datasource.WriteGitHubDataSource
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse
import br.com.jhonny.starlord.feature.home.dto.LicenseDTO
import br.com.jhonny.starlord.feature.home.dto.RepositoryOwnerDTO
import br.com.jhonny.starlord.rule.TestCoroutineScopeRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class GitHubRepositoryImplTest {
    @get:Rule
    private val scope = TestCoroutineScopeRule()
    private val localDatasource = mockk<WriteGitHubDataSource>(relaxed = true)
    private val remoteDatasource = mockk<ReadGitHubDatasource>()
    private val pageManager = mockk<PageDatasource>(relaxed = true)
    private val repository = GitHubRepositoryImpl(
        localDatasource = localDatasource,
        remoteDatasource = remoteDatasource,
        pageManager = pageManager,
    )

    @Before
    fun setUp() {
        every { pageManager.page } returns 1
    }

    @Test
    fun `should successfully retrieve repositories from local datasource`() = runTest {
        coEvery { localDatasource.getRepositories(any()) } returns expectedResponse

        val result = repository.getRepositories()

        assertEquals(expectedResponse.items, result)
    }

    @Test
    fun `should successfully retrieve repositories from remote datasource`() = runTest {
        coEvery { localDatasource.getRepositories(any()) } returns null
        coEvery { remoteDatasource.getRepositories(any()) } returns expectedResponse

        val result = repository.getRepositories()

        assertEquals(expectedResponse.items, result)
    }

    @Test
    fun `should successfully retrieve repositories with multiple pages`() = runTest {
        every { pageManager.page } returns 2
        coEvery { localDatasource.getRepositories(any()) } returns expectedResponse
        coEvery { remoteDatasource.getRepositories(any()) } returns expectedResponse

        val result = repository.getRepositories()

        assertTrue(result.size == 2)
    }

    @Test
    fun `should fail retrieve repositories`() = runTest {
        coEvery { localDatasource.getRepositories(any()) } returns null
        coEvery { remoteDatasource.getRepositories(any()) } returns null

        val result = repository.getRepositories()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `should successfully retrieve repository`() = runTest {
        coEvery { localDatasource.getRepositories(any()) } returns expectedResponse

        val result = repository.getRepository(1)

        assertEquals(repositoryDTO, result)
    }

    @Test
    fun `should fail retrieve repository with missing data on local datasource`() = runTest {
        coEvery { localDatasource.getRepositories(any()) } returns expectedResponse

        val result = repository.getRepository(2)

        assertNull(result)
    }

    @Test
    fun `should fail retrieve repository with empty local datasource`() = runTest {
        coEvery { localDatasource.getRepositories(any()) } returns null

        val result = repository.getRepository(1)

        assertNull(result)
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
