package br.com.jhonny.starlord.feature.home.dto

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
    @SerialName("description")
    val description: String?,
    @SerialName("language")
    val language: String,
    @SerialName("license")
    val license: LicenseDTO?,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("pushed_at")
    val pushedAt: String,
    @SerialName("watchers_count")
    val watchersCount: Int,
    @SerialName("open_issues")
    val openIssuesCount: Int,
)

@Serializable
public data class LicenseDTO(
    @SerialName("name")
    val name: String,
)

@Serializable
public data class RepositoryOwnerDTO(
    @SerialName("login")
    val author: String,
    @SerialName("avatar_url")
    val avatar: String,
)
