package br.com.jhonny.starlord.feature.home.datasource

/**
 * Interface responsible for managing pagination.
 */
internal interface PageDatasource {
    /**
     * Represents the current page number.
     * This property holds the value of the page being accessed or displayed.
     */
    val page: Int
    /**
     * Increments the current page number.
     */
    fun increment()
    /**
     * Resets the current page to the initial value.
     */
    fun reset()
}
