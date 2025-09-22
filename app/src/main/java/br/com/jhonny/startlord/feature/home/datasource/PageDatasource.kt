package br.com.jhonny.startlord.feature.home.datasource

internal interface PageDatasource {
    val page: Int
    fun increment()
    fun reset()
}
