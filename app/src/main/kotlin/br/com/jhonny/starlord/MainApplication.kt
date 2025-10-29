package br.com.jhonny.starlord

import android.app.Application
import br.com.jhonny.starlord.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

public class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(mainModule)
        }
    }
}
