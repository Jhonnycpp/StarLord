package br.com.jhonny.starlord.feature.home.datasource

import org.junit.Assert.assertEquals
import org.junit.Test

class PageManagerDatasourceTest {
    private val pageManager: PageDatasource = PageManagerDatasource()

    @Test
    fun `should increment page`() {
        pageManager.increment()
        assertEquals(2, pageManager.page)
    }

    @Test
    fun `should reset page`() {
        pageManager.increment()
        pageManager.reset()
        assertEquals(1, pageManager.page)
    }

    @Test
    fun `should get page`() {
        assertEquals(1, pageManager.page)
    }
}
