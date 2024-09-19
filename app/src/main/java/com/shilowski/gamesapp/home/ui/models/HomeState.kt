package com.shilowski.gamesapp.home.ui.models

import com.shilowski.gamesapp.common.domain.interfaces.Identifiable
import com.shilowski.gamesapp.common.domain.interfaces.UiState
import kotlinx.parcelize.Parcelize

// todo: add other fields
@Parcelize
data class HomeState(
    override val id: String,

) : Identifiable, UiState {
    // todo: show error message if no name was provided
    // todo: we need to change the buttons and description if there are no permissions granted for notifications
    val permissionText =
        "To enable game controls and receive important updates, please tap 'Allow' for notification permissions. This ensures a smooth gaming experience, keeping you in sync with the action. Let's make your gameplay seamless – just a quick tap away!"
    val nameErrorText =
        "Oops! Please provide a valid name to continue. Your name should only contain letters or numbers, and no spaces."
    val generatedText =
        "This game is designed for local play, fostering real-life connections by requiring all participants to be on the same wifi network. Gather in a shared space, connect to the same wifi, and enjoy a collaborative gaming experience in person. \n\nCreate your own game or join others by entering the room code after pressing 'Join Game'. Make the most of your gaming night by choosing your favorite board game and inviting others to join the fun!"

}
