package com.shilowski.gamesapp.network.data.plugins

import com.shilowski.gamesapp.network.TempSessionUser
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCallPipeline
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie
import io.ktor.server.sessions.get
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import io.ktor.util.generateNonce
import java.util.UUID

// is responsible for controlling the session of the server and each connected user

// todo: add SessionUser interface that'd contain the websocket link and information about the user
//  ex: https://github.com/philipplackner/ChatApp-Server/blob/master/src/main/kotlin/com/plcoding/room/Member.kt

// todo: add SessionController interface that'd be responsible for managing connected users and the game
//  ex: https://github.com/philipplackner/ChatApp-Server/blob/master/src/main/kotlin/com/plcoding/room/RoomController.kt

private const val SESSION_KEY = "SESSION"

fun Application.configureSessions() {
    install(Sessions) {
        cookie<TempSessionUser>(SESSION_KEY)
    }

    // here we assign a session
    // this intercepts each request and if there's a session data attached it will assign the id
    // somehow here we get the information about a user who's trying to connect
    intercept(ApplicationCallPipeline.Features) {
        if(call.sessions.get<TempSessionUser>() == null) {
            // this is how to get data from the call:
            // call.parameters["username"] ?: "Guest"
            val userId = UUID.randomUUID().toString()
            val randomId = generateNonce() // generates random string
            // todo: get the owner flag somehow
            call.sessions.set(TempSessionUser(userId, randomId))
        }
    }
}