package br.com.jhonny.starlord.feature.home.usecase

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.dto.LicenseDTO
import br.com.jhonny.starlord.feature.home.dto.RepositoryOwnerDTO
import br.com.jhonny.starlord.feature.home.repository.GitHubRepository
import br.com.jhonny.starlord.rule.TestCoroutineScopeRule
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import java.util.Date
import kotlin.test.assertFailsWith

class RetrieveGitHubRepositoryUseCaseTest {
    @get:Rule
    private val scope = TestCoroutineScopeRule()
    private val gitHubRepository = mockk<GitHubRepository>(relaxed = true)
    private val useCase = RetrieveGitHubRepositoryUseCase(gitHubRepository)

    @Test
    fun `should successfully retrieve repositories from GitHubRepository`() = runTest {
        coEvery { gitHubRepository.getRepositories(any(), any()) } returns listOf(input)

        val result = useCase("query", listOf("language"))

        assertEquals(expectedResult, result)
        coVerify { gitHubRepository.getRepositories("query", listOf("language")) }
    }

    @Test
    fun `should return an empty list when GitHubRepository returns an empty list`() = runTest {
        coEvery { gitHubRepository.getRepositories(any(), any()) } returns emptyList()

        val result = useCase("query", listOf("language"))

        assertEquals(emptyList<RepositoryVO>(), result)
        coVerify { gitHubRepository.getRepositories("query", listOf("language")) }
    }

    @Test
    fun `should throw an exception when GitHubRepository throws an exception`() = runTest {
        val expectedException = RuntimeException("Test exception")
        coEvery { gitHubRepository.getRepositories(any(), any()) } throws expectedException

        val exception = assertFailsWith<RuntimeException> {
            useCase("query", listOf("language"))
        }

        assertEquals(expectedException, exception)
        coVerify { gitHubRepository.getRepositories("query", listOf("language")) }
    }

    @Test
    fun `should call repository with trimmed and lowercased query`() = runTest {
        val query = "  Query With Spaces And UpperCase  "
        val expectedQuery = "query with spaces and uppercase"

        useCase(query, listOf("kotlin"))

        coVerify { gitHubRepository.getRepositories(expectedQuery, listOf("kotlin")) }
    }

    @Test
    fun `should call repository with trimmed, lowercased, and sorted languages`() = runTest {
        val languages = listOf("  Kotlin  ", "Java  ", "  Swift")
        val expectedLanguages = listOf("java", "kotlin", "swift")

        useCase("query", languages)

        coVerify { gitHubRepository.getRepositories("query", expectedLanguages) }
    }

    @Test
    fun `should call repository with fully processed query and languages`() = runTest {
        val query = "  My Query  "
        val languages = listOf("  Kotlin  ", "Java  ")
        val expectedQuery = "my query"
        val expectedLanguages = listOf("java", "kotlin")

        useCase(query, languages)

        coVerify { gitHubRepository.getRepositories(expectedQuery, expectedLanguages) }
    }

    private companion object {
        val expectedResult = listOf(
            RepositoryVO(
                id = 1,
                name = "name",
                description = "description",
                author = "author",
                userAvatar = "avatar",
                language = "language",
                licenseName = "Apache License 2.0",
                createdAt = Date(0),
                updatedAt = Date(0),
                pushedAt = Date(0),
                watcherCount = 1,
                issueCount = 1,
                starCount = 1,
                forkCount = 1,
            )
        )
        val input = GitHubRepositoryDTO(
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
            createdAt = "1970-01-01T00:00:00Z",
            updatedAt = "1970-01-01T00:00:00Z",
            pushedAt = "1970-01-01T00:00:00Z",
            watchersCount = 1,
            openIssuesCount = 1,
            starCount = 1,
        )
    }
}
