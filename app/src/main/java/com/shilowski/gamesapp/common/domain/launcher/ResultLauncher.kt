package com.shilowski.gamesapp.common.domain.launcher

import androidx.activity.result.ActivityResultLauncher

class ResultLauncher<I> (
    private val launcher: ActivityResultLauncher<I>,
    private val input: I,
    private val onLaunchCheck: () -> Boolean
) : Launcher {

    override fun launch() {
        if (onLaunchCheck.invoke()) {
            launcher.launch(input)
        }
    }
}