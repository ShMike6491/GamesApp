package com.shilowski.gamesapp.common.domain.launcher

import android.app.Activity
import androidx.activity.result.contract.ActivityResultContract

interface ResultHandler<I, O> {

    val requestTag: String

    val requestContract: ActivityResultContract<I, O>

    fun getRequestInput(activity: Activity): I

    fun handleResult(result: O)

    /**
     * Return value determines whether it should proceed with launch or not
     * [false] means activity result launcher's launch function should not be called
     * [true] means launch function will be called by activity result API
     */
    fun doOnLaunch(): Boolean
}