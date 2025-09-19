package br.com.jhonny.startlord.feature.home

import br.com.jhonny.startlord.extension.toDate
import br.com.jhonny.startlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.startlord.feature.home.repository.GitHubRepository
import br.com.jhonny.startlord.ui.screen.home.vo.RepositoryVO

public class RetrieveGitHubRepositoryUseCase(
    private val repository: GitHubRepository,
) {

    public suspend operator fun invoke(): List<RepositoryVO> = repository.getRepositories().toRepositoriesVO()

    private fun List<GitHubRepositoryDTO>.toRepositoriesVO() = map {
        RepositoryVO(
            id = it.id,
            name = it.name,
            author = it.owner.author,
            userAvatar = it.owner.avatar,
            description = it.description.orEmpty(),
            language = it.language,
            licenseName = it.license?.name,
            createdAt = it.createdAt.toDate(),
            updatedAt = it.updatedAt.toDate(),
            pushedAt = it.pushedAt.toDate(),
            starCount = it.starCount,
            forkCount = it.forksCount,
            watcherCount = it.watchersCount,
            issueCount = it.openIssuesCount,
        )
    }
}
