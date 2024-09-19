package com.shilowski.gamesapp.network.domain

import com.shilowski.gamesapp.network.data.module
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine

class EmbaddedServer {

    // todo: add logs
    //  make as an abstract class with game passed as a param
    //  pass or generate random room code

    private var server: NettyApplicationEngine? = null

    suspend fun startServer() {
        if (server == null) {
            server = embeddedServer(Netty, port = PORT, module = Application::module)
        }

        server?.start(wait = true)
    }

    suspend fun stopServer() {
        server?.stop(0, 0)
        server = null
    }

    companion object {
        const val PORT = 8008
        const val HOST = "localhost"
    }
}