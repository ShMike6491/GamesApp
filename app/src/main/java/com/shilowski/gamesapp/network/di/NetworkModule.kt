package com.shilowski.gamesapp.network.di

import com.shilowski.gamesapp.network.domain.NetworkClientService
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> {
        HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
            install(ContentNegotiation) {
                json()
            }
        }
    }

    single { NetworkClientService(get()) }
}