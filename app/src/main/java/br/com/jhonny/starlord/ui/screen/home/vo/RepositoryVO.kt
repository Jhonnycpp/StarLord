package br.com.jhonny.starlord.ui.screen.home.vo

import java.util.Date

public data class RepositoryVO(
    val id: Int,
    val name: String,
    val author: String,
    val userAvatar: String,
    val description: String,
    val language: String,
    val licenseName: String?,
    val starCount: Int,
    val forkCount: Int,
    val watcherCount: Int,
    val issueCount: Int,
    val createdAt: Date,
    val updatedAt: Date,
    val pushedAt: Date,
)
