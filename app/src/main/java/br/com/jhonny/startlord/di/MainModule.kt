package br.com.jhonny.startlord.di

import br.com.jhonny.startlord.ui.screen.home.HomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}

private val adapterModule = module {

}

private val repositoryModule = module {

}

public val mainModule: Module = module {
    includes(
        viewModelModule,
        adapterModule,
        repositoryModule,
    )
}
