package br.com.jhonny.starlord.feature.home.usecase

import br.com.jhonny.starlord.extension.toDate
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.repository.GitHubRepository
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO

public class RetrieveGitHubRepositoryUseCase(
    private val repository: GitHubRepository,
) {

    public suspend operator fun invoke(
        searchTerm: String,
        languages: List<String>
    ): List<RepositoryVO> = repository.getRepositories(
        searchTerm = searchTerm.trim().lowercase(),
        languages = languages.map { it.trim().lowercase() }.sorted(),
    ).toRepositoriesVO()

    private fun List<GitHubRepositoryDTO>.toRepositoriesVO(): List<RepositoryVO> = map {
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
