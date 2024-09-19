package com.shilowski.gamesapp.network.data

import com.shilowski.gamesapp.network.TempSessionController
import com.shilowski.gamesapp.network.data.plugins.configureRouting
import com.shilowski.gamesapp.network.data.plugins.configureSerialization
import com.shilowski.gamesapp.network.data.plugins.configureSessions
import com.shilowski.gamesapp.network.data.plugins.configureSockets
import io.ktor.server.application.Application

// this is an entry point of the server
// (just like Application class in android)
fun Application.module() {
    // todo: move outside of the configuration
    //  should be configured individually for each server
    val controller = TempSessionController()

    configureSockets()
    configureSerialization()
    configureSessions()
    // we could pass our routs dynamically
    // along with the rout code (ex: "XYZW")
    configureRouting(controller)
}