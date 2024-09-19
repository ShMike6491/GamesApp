package com.shilowski.gamesapp.common.ui.dialogs

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.shilowski.gamesapp.common.ui.containers.PrimaryBox

@Composable
fun PrimaryAlertDialog(
    modifier: Modifier = Modifier,
    onDismissAction: () -> Unit,
    dismissOnBackPress: Boolean = true,
    dismissOnOutsideClick: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) {
    Dialog(
        onDismissRequest = onDismissAction,
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnOutsideClick
        ),
        content = {
            PrimaryBox(
                modifier = modifier.fillMaxWidth(),
                content = content,
                backgroundColor = MaterialTheme.colorScheme.background,
                strokeColor = MaterialTheme.colorScheme.onBackground
            )
        }
    )
}