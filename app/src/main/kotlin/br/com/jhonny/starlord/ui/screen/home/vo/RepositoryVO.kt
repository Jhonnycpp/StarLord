package br.com.jhonny.starlord.ui.screen.home.vo

import java.util.Date

/**
 * Data class representing a repository.
 *
 * @property id The unique identifier of the repository.
 * @property name The name of the repository.
 * @property author The author of the repository.
 * @property userAvatar The URL of the author's avatar.
 * @property description A brief description of the repository.
 * @property language The primary programming language used in the repository. Can be null if not specified.
 * @property licenseName The name of the license under which the repository is distributed. Can be null if not specified.
 * @property starCount The number of stars the repository has received.
 * @property forkCount The number of times the repository has been forked.
 * @property watcherCount The number of users watching the repository.
 * @property issueCount The number of open issues in the repository.
 * @property createdAt The date and time when the repository was created.
 * @property updatedAt The date and time when the repository was last updated.
 * @property pushedAt The date and time when the repository was last pushed to.
 */
public data class RepositoryVO(
    val id: Int,
    val name: String,
    val author: String,
    val userAvatar: String,
    val description: String,
    val language: String?,
    val licenseName: String?,
    val starCount: Int,
    val forkCount: Int,
    val watcherCount: Int,
    val issueCount: Int,
    val createdAt: Date,
    val updatedAt: Date,
    val pushedAt: Date,
)
