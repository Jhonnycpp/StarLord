package br.com.jhonny.startlord.feature.home.repository

import br.com.jhonny.startlord.feature.home.dto.GitHubRepositoryDTO

public interface GitHubRepository {
    public suspend fun getRepositories(page: Int): List<GitHubRepositoryDTO>
}
