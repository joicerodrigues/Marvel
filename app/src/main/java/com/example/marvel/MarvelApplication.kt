package com.example.marvel

import android.app.Application
import com.example.marvel.ui.marvelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MarvelApplication)
            modules(marvelModule)
        }
    }
}