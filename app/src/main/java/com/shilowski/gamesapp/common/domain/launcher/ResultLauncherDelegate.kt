package com.shilowski.gamesapp.common.domain.launcher

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment

fun <I, O> ComponentActivity.resultLauncherProvider(
    getter: () -> ResultHandler<I, O>
) = lazy(LazyThreadSafetyMode.NONE) {
    val handler = getter.invoke()
    val input = handler.getRequestInput(this)
    val launcher = activityResultRegistry.register(
        handler.requestTag,
        handler.requestContract,
        handler::handleResult
    )

    ResultLauncher(launcher, input, handler::doOnLaunch) as Launcher
}

fun <I, O> Fragment.resultLauncherProvider(
    getter: () -> ResultHandler<I, O>
) = lazy(LazyThreadSafetyMode.NONE) {
    val handler = getter.invoke()
    val activity = requireActivity()
    val input = handler.getRequestInput(activity)
    val launcher = activity.activityResultRegistry.register(
        handler.requestTag,
        handler.requestContract,
        handler::handleResult
    )

    ResultLauncher(launcher, input, handler::doOnLaunch) as Launcher
}