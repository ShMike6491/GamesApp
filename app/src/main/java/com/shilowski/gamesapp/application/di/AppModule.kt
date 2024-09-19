package com.shilowski.gamesapp.application.di

import com.shilowski.gamesapp.network.di.networkModule
import org.koin.dsl.module

val appModule = module {}

val appModules = listOf(appModule, networkModule)