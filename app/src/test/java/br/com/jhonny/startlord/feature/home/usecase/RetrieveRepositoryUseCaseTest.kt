package br.com.jhonny.startlord.feature.home.usecase

import br.com.jhonny.startlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.startlord.feature.home.dto.LicenseDTO
import br.com.jhonny.startlord.feature.home.dto.RepositoryOwnerDTO
import br.com.jhonny.startlord.feature.home.repository.GitHubRepository
import br.com.jhonny.startlord.ui.screen.home.vo.RepositoryVO
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.util.Date

class RetrieveRepositoryUseCaseTest {
    private val repository = mockk<GitHubRepository>()
    private val useCase = RetrieveRepositoryUseCase(repository)

    @Test
    fun `should successfully retrieve repository`() = runTest {
        coEvery { repository.getRepository(any()) } returns input

        val result = useCase(1)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `should return null when repository is not found`() = runTest {
        coEvery { repository.getRepository(any()) } returns null

        val result = useCase(1)

        assertNull(result)
    }

    private companion object {
        val expectedResult = RepositoryVO(
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
