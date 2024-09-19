package com.shilowski.gamesapp.common.domain.models

import android.content.Context
import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.parcelize.Parcelize

sealed class UiString : Parcelable{

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(id = resId, args.map { it.asString() })
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, args.map { it.asString(context) } )
        }
    }

    @Parcelize
    data class DynamicString(val value: String): UiString()

    @Parcelize
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: UiString
    ): UiString()
}

fun String.asUiString(): UiString = UiString.DynamicString(this)