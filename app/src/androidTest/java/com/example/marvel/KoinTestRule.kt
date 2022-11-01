package com.example.marvel

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.rules.TestWatcher
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.module.Module

open class KoinTestRule(
    private val modules: List<Module>
) : TestWatcher() {
    override fun starting(description: org.junit.runner.Description) {
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext.applicationContext)
            modules(modules)
        }
    }
    override fun finished(description: org.junit.runner.Description) {
        stopKoin()
    }
}