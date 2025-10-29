package br.com.jhonny.starlord.feature.home.usecase

import br.com.jhonny.starlord.extension.toDate
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.repository.GitHubRepository
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO

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
