package br.com.jhonny.starlord.feature.home.datasource

internal interface PageDatasource {
    val page: Int
    fun increment()
    fun reset()
}
