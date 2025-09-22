package br.com.jhonny.starlord.di

import br.com.jhonny.starlord.feature.home.datasource.LocalGitHubDatasource
import br.com.jhonny.starlord.feature.home.datasource.PageDatasource
import br.com.jhonny.starlord.feature.home.datasource.PageManagerDatasource
import br.com.jhonny.starlord.feature.home.datasource.ReadGitHubDatasource
import br.com.jhonny.starlord.feature.home.datasource.RemoteGitHubDatasource
import br.com.jhonny.starlord.feature.home.datasource.WriteGitHubDataSource
import br.com.jhonny.starlord.feature.home.repository.GitHubRepository
import br.com.jhonny.starlord.feature.home.repository.GitHubRepositoryImpl
import br.com.jhonny.starlord.feature.home.service.GitHubRepositoryService
import br.com.jhonny.starlord.feature.home.usecase.RetrieveGitHubRepositoryUseCase
import br.com.jhonny.starlord.feature.home.usecase.RetrieveRepositoryUseCase
import br.com.jhonny.starlord.ui.navigation.Navigation
import br.com.jhonny.starlord.ui.navigation.NavigationManager
import br.com.jhonny.starlord.ui.screen.home.detail.DetailViewModel
import br.com.jhonny.starlord.ui.screen.home.list.HomeViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}

private val useCaseModule = module {
    factoryOf(::RetrieveGitHubRepositoryUseCase)
    factoryOf(::RetrieveRepositoryUseCase)
}

private val repositoryModule = module {
    factory<ReadGitHubDatasource> {
        RemoteGitHubDatasource(service = get())
    }

    single<WriteGitHubDataSource> {
        LocalGitHubDatasource()
    }

    single<PageDatasource> {
        PageManagerDatasource()
    }

    factory<GitHubRepository> {
        GitHubRepositoryImpl(
            localDatasource = get(),
            remoteDatasource = get(),
            pageManager = get(),
        )
    }
}

private val networkModule = module {
    single<Retrofit> {
        val contentType = "application/json".toMediaType()
        val json: Json = get()
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    factory<GitHubRepositoryService> {
        get<Retrofit>().create(GitHubRepositoryService::class.java)
    }
}

public val mainModule: Module = module {
    includes(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        networkModule,
    )

    single<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }

    single<Navigation> {
        NavigationManager()
    }
}
