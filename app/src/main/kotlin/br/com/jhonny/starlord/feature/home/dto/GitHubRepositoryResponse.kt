package br.com.jhonny.starlord.feature.home.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the response from the GitHub API when searching for repositories.
 *
 * @property items A list of [GitHubRepositoryDTO] objects, each representing a repository found.
 * @property totalCount The total number of repositories that matched the search query.
 */
@Serializable
public data class GitHubRepositoryResponse(
    @SerialName("items")
    val items: List<GitHubRepositoryDTO>,
    @SerialName("total_count")
    val totalCount: Int,
)

/**
 * Represents a GitHub repository.
 *
 * @property id The unique identifier of the repository.
 * @property name The name of the repository.
 * @property owner The owner of the repository.
 * @property starCount The number of stars the repository has.
 * @property forksCount The number of forks the repository has.
 * @property description A description of the repository.
 * @property language The primary programming language used in the repository.
 * @property license The license under which the repository is distributed.
 * @property createdAt The date and time when the repository was created.
 * @property updatedAt The date and time when the repository was last updated.
 * @property pushedAt The date and time when the repository was last pushed to.
 * @property watchersCount The number of users watching the repository.
 * @property openIssuesCount The number of open issues in the repository.
 */
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
    val language: String?,
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

/**
 * Data class representing a license for a GitHub repository.
 *
 * @property name The name of the license (e.g., "MIT License", "GNU General Public License v3.0").
 */
@Serializable
public data class LicenseDTO(
    @SerialName("name")
    val name: String,
)

/**
 * Data class representing the owner of a GitHub repository.
 *
 * @property author The login name of the repository owner.
 * @property avatar The URL of the repository owner's avatar.
 */
@Serializable
public data class RepositoryOwnerDTO(
    @SerialName("login")
    val author: String,
    @SerialName("avatar_url")
    val avatar: String,
)
