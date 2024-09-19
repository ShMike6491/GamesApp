package com.shilowski.gamesapp.network

import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import java.lang.IllegalArgumentException
import java.util.Collections
import java.util.concurrent.ConcurrentHashMap

// todo: change to an interface and move to domain layer
class TempSessionController {

    private val connections = ConcurrentHashMap<String, WebSocketSession>()
    private val messages = Collections.synchronizedList(mutableListOf<String>())

    fun connectUser(
        userId: String,
        socket: WebSocketSession
    ) {
        if(connections.containsKey(userId)) {
            val message = "user with this id was already connected"
            throw IllegalArgumentException(message)
        }

        // todo: if collection is empty we should add the user with group owner flag on
        val isOwner = connections.isEmpty()
        val updatedId = if (isOwner) "$userId(*)" else userId
        connections[updatedId] = socket
    }

    suspend fun sendMessage(senderId: String, message: String) {
        val isOwner = connections["$senderId(*)"] != null
        val userWithTag = if (isOwner) "$senderId(*)" else senderId
        val messageEntity = "$userWithTag: $message"

        messages.add(messageEntity)

        connections.values.forEach { connection ->
            // needed when we start sending actual data
            // val parsedMessage = encodeToString(messageEntity)
            connection.send(Frame.Text(messageEntity))
        }
    }

    suspend fun getAllMessages(): List<String> {
        return messages
    }

    suspend fun tryDisconnect(userId: String) {
        connections[userId]?.close()
        if(connections.containsKey(userId)) {
            connections.remove(userId)
        }
    }
}