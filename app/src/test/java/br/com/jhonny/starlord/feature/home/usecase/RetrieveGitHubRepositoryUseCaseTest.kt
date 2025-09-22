package br.com.jhonny.starlord.feature.home.usecase

import br.com.jhonny.starlord.TestScopeRule
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.dto.LicenseDTO
import br.com.jhonny.starlord.feature.home.dto.RepositoryOwnerDTO
import br.com.jhonny.starlord.feature.home.repository.GitHubRepository
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import java.util.Date

class RetrieveGitHubRepositoryUseCaseTest {
    @get:Rule
    private val scope = TestScopeRule()
    private val gitHubRepository = mockk<GitHubRepository>()
    private val useCase = RetrieveGitHubRepositoryUseCase(gitHubRepository)

    @Test
    fun `should successfully retrieve repositories from GitHubRepository`() = runTest {
        coEvery { gitHubRepository.getRepositories() } returns listOf(input)

        val result = useCase()

        assertEquals(expectedResult, result)
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
