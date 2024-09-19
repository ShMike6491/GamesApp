package com.shilowski.gamesapp.network.data.plugins

import android.util.Log
import com.shilowski.gamesapp.network.TempSessionController
import com.shilowski.gamesapp.network.TempSessionUser
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.sessions.get
import io.ktor.server.sessions.sessions
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.channels.consumeEach
import java.lang.IllegalArgumentException

// todo: pass custom routing for each game instead?
const val ROUTE = "/temp"
const val MESSAGES_ROUTE = "/messages"

fun Application.configureRouting(controller: TempSessionController) {
    println("CUSTOM TEST: routing was called")

    routing {
        webSocket(ROUTE) {
            val session = call.sessions.get<TempSessionUser>()
            if(session == null) {
                val policyCode = CloseReason.Codes.VIOLATED_POLICY
                val closeReason = "No session found."
                close(CloseReason(policyCode, closeReason))
                return@webSocket
            }

            try {
                controller.connectUser(
                    userId = session.userId,
                    //isOwner = session.isServerOwner,
                    socket = this
                )
                incoming.consumeEach { frame ->
                    if(frame is Frame.Text) {
                        controller.sendMessage(
                            senderId = session.userId,
                            message = frame.readText()
                        )
                    }
                }
            } catch(e: IllegalArgumentException) {
                println("CUSTOM TEST: illegal state error")

                call.respond(HttpStatusCode.Conflict)
            } catch (e: Exception) {
                println("CUSTOM TEST: web socket initialization error")

                Log.e("TEST", e.message ?: "")
                e.printStackTrace()
            } finally {
                controller.tryDisconnect(session.userId)
            }
        }

        get(MESSAGES_ROUTE) {
            call.respond(
                HttpStatusCode.OK,
                controller.getAllMessages()
            )
        }

        get("/test") {
            call.respond(
                HttpStatusCode.OK,
                "hello world!"
            )
        }
    }
}