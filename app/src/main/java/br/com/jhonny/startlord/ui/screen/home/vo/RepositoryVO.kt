package br.com.jhonny.startlord.ui.screen.home.vo

public data class RepositoryVO(
    val id: Int,
    val name: String,
    val author: String,
    val starCount: Int,
    val forkCount: Int,
    val imageUrl: String,
)
