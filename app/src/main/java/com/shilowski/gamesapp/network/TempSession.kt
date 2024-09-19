package com.shilowski.gamesapp.network

// todo: change to an interface and move to domain layer
data class TempSessionUser(
    val userId: String,
    val sessionId: String,
    //val isServerOwner: Boolean,
)