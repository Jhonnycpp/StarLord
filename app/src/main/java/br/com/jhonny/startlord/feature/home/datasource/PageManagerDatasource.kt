package br.com.jhonny.startlord.feature.home.datasource

import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.concurrent.atomics.incrementAndFetch

@OptIn(ExperimentalAtomicApi::class)
internal class PageManagerDatasource : PageDatasource {
    private var _page = AtomicInt(1)
    override val page: Int
        get() = _page.load()

    override fun increment() {
        _page.incrementAndFetch()
    }

    override fun reset() {
        _page.store(1)
    }
}
