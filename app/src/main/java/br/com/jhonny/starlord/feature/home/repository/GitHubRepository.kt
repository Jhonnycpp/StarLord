package br.com.jhonny.starlord.feature.home.repository

import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO

public interface GitHubRepository {
    public suspend fun getRepositories(): List<GitHubRepositoryDTO>
    public suspend fun getRepository(id: Int): GitHubRepositoryDTO?
}
