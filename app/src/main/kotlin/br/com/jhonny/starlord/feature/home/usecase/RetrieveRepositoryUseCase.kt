package br.com.jhonny.starlord.feature.home.usecase

import br.com.jhonny.starlord.extension.toDate
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.repository.GitHubRepository
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO

/**
 * Use case responsible for retrieving a single GitHub repository by its ID.
 *
 * This class fetches a repository from the data layer [GitHubRepository]
 * and converts the resulting [GitHubRepositoryDTO] into a [RepositoryVO]
 * suitable for presentation in the UI.
 *
 * @property repository The data source for fetching repository information.
 */
public class RetrieveRepositoryUseCase(
    private val repository: GitHubRepository,
) {
    public suspend operator fun invoke(id: Int): RepositoryVO? {
        return repository.getRepository(id)?.toRepositoryVO()
    }

    private fun GitHubRepositoryDTO.toRepositoryVO() = RepositoryVO(
        id = id,
        name = name,
        author = owner.author,
        userAvatar = owner.avatar,
        description = description.orEmpty(),
        language = language,
        licenseName = license?.name,
        createdAt = createdAt.toDate(),
        updatedAt = updatedAt.toDate(),
        pushedAt = pushedAt.toDate(),
        starCount = starCount,
        forkCount = forksCount,
        watcherCount = watchersCount,
        issueCount = openIssuesCount,
    )
}
