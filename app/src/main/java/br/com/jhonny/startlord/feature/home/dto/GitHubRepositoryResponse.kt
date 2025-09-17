package br.com.jhonny.startlord.feature.home.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class GitHubRepositoryResponse(
    @SerialName("items")
    val items: List<GitHubRepositoryDTO>,
    @SerialName("total_count")
    val totalCount: Int,
)

@Serializable
public data class GitHubRepositoryDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("owner")
    val owner: RepositoryOwnerDTO,
    @SerialName("stargazers_count")
    val starCount: Int,
    @SerialName("forks_count")
    val forksCount: Int,
)

@Serializable
public data class RepositoryOwnerDTO(
    @SerialName("login")
    val author: String,
    @SerialName("avatar_url")
    val avatar: String,
)
