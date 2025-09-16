package br.com.jhonny.startlord

import android.app.Application
import br.com.jhonny.startlord.di.mainModule
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
