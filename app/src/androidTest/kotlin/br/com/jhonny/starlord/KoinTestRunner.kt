package br.com.jhonny.starlord

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

/**
 * Custom [AndroidJUnitRunner] for Koin.
 *
 * This runner is responsible for replacing the default application class with a test-specific
 * one ([KoinAppTest]) during instrumentation tests. This allows for the initialization of a
 * custom Koin dependency graph tailored for testing purposes, such as using mock dependencies.
 */
class KoinTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        classLoader: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(classLoader, KoinAppTest::class.java.name, context)
    }
}
