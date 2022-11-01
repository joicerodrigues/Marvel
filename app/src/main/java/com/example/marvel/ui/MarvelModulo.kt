package com.example.marvel.ui

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val marvelModule = module {
    viewModel {
        MarvelViewModel()
    }
}