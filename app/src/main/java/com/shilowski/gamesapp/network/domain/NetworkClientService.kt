package com.shilowski.gamesapp.network.domain

import com.shilowski.gamesapp.network.data.plugins.MESSAGES_ROUTE
import com.shilowski.gamesapp.network.data.plugins.ROUTE
import com.shilowski.gamesapp.network.domain.EmbaddedServer.Companion.HOST
import com.shilowski.gamesapp.network.domain.EmbaddedServer.Companion.PORT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive

// todo: move to each module individually
//  except for initialization? (use an abstract class??)
class NetworkClientService(
    private val client: HttpClient
) {

    private var socket: WebSocketSession? = null

    // todo: remove username?
    // initialized session might have to start a foreground service to keep the process alive?
    suspend fun initSession(username: String, callback: () -> Unit) {
        try {
            socket = client.webSocketSession {
//                by specifying "?smth=value" we can pass parameters to the session
//                url("${ChatSocketService.Endpoints.ChatSocket.url}?username=$username")
                url(BASE_WS_ENDPOINT)
            }

            if(socket?.isActive == true) {
                callback.invoke()
            }
        } catch(e: Exception) {
            println("CUSTOM TEST: error on initialization of websocket")
            if (e is CancellationException) throw e
            e.printStackTrace()
        }
    }

    suspend fun closeSession() {
        socket?.close()
    }

    suspend fun sendMessage(message: String) {
        try {
            socket?.send(Frame.Text(message))
        } catch (e: Exception) {
            println("CUSTOM TEST: error sending a message")
            if (e is CancellationException) throw e
            e.printStackTrace()
        }
    }

    fun observeMessages(): Flow<String> {
        return try {
            socket?.incoming
                ?.receiveAsFlow()
                ?.filter { it is Frame.Text }
                ?.map { (it as? Frame.Text)?.readText() ?: "" }
                ?: emptyFlow()

//            an example of how to decode json responses
//                ?.map {
//                    val json = (it as? Frame.Text)?.readText() ?: ""
//                    val messageDto = Json.decodeFromString<MessageDto>(json)
//                    messageDto.toMessage()
//                }
        } catch(e: Exception) {
            println("CUSTOM TEST: error observing the messages")
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyFlow()
        }
    }

    suspend fun getAllMessages(): List<String> {
        return try {
            client.get(BASE_MSG_ENDPOINT).body()
        } catch (e: Exception) {
            println("CUSTOM TEST: error getting messages ${e.message}")
            if (e is CancellationException) throw e
            listOf("something went wrong")
        }
    }

    companion object {
        const val BASE_WS_ENDPOINT = "ws://$HOST:$PORT/$ROUTE"
        const val BASE_MSG_ENDPOINT = "http://$HOST:$PORT/$MESSAGES_ROUTE"
    }

// region REMOVE_LATER
//    private fun closeSession() {
//        api.closeSession()
//    }
//
//    private fun joinSession() {
//        println("CUSTOM TEST: join session was called")
//
//        lifecycleScope.launch(Dispatchers.IO) {
////            _messages.value = api.getAllMessages()
//
//            api.initSession("test") {
//                println("CUSTOM TEST: web socket initialized successfully")
//
//                api.observeMessages()
//                    .onEach { message ->
//                        _messages.value = messages.value.plus(message)
//                    }
//                    .launchIn(lifecycleScope)
//
//                println("CUSTOM TEST: subscribed to messages")
//            }
//        }
//    }
//
//    private fun sendMessage(message: String) {
//        lifecycleScope.launch(Dispatchers.IO) {
//            api.sendMessage(message)
//            println("CUSTOM TEST: message was sent")
//        }
//    }
// endregion REMOVE_LATER

}