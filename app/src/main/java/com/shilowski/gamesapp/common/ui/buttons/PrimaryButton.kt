package com.shilowski.gamesapp.common.ui.buttons

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.shilowski.gamesapp.common.domain.models.UiString
import com.shilowski.gamesapp.common.domain.models.asUiString
import com.shilowski.gamesapp.common.ui.extensions.asDp
import com.shilowski.gamesapp.common.ui.extensions.asPx
import com.shilowski.gamesapp.common.ui.text.HeaderMainText

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: UiString,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    strokeWidth: Dp = 4.dp,
    strokeColor: Color = MaterialTheme.colorScheme.onBackground,
    onClick: () -> Unit = {},
) {
    var inPressedState by remember { mutableStateOf(false) }
    val pressedOffset by animateFloatAsState(
        if (inPressedState) 0f else strokeWidth.asPx(),
        label = ""
    )
    val idleOffset by animateFloatAsState(
        if (inPressedState) strokeWidth.asPx() else 0f,
        label = ""
    )

    val paddingOffset = pressedOffset.asDp() / 2
    val paddingIdleOffset = idleOffset.asDp() / 2
    val boxBorderWidth = strokeWidth.run { value - (value * 0.25f) }.dp

    Box(
        modifier = modifier
            .drawBehind {
                drawLine(
                    color = strokeColor,
                    start = Offset(pressedOffset, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = pressedOffset
                )
                drawLine(
                    color = strokeColor,
                    start = Offset(size.width, pressedOffset),
                    end = Offset(size.width, (size.height + paddingOffset.toPx())),
                    strokeWidth = paddingOffset.toPx()
                )
            }
            .padding(
                bottom = paddingOffset,
                end = paddingOffset / 2,
                top = paddingIdleOffset,
                start = paddingIdleOffset / 2
            )
            .background(backgroundColor)
            .border(boxBorderWidth, strokeColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .pointerInput(inPressedState) {
                awaitPointerEventScope {
                    inPressedState = if (inPressedState) {
                        waitForUpOrCancellation()
                        false
                    } else {
                        awaitFirstDown(false)
                        true
                    }
                }
            },
        content = {
            HeaderMainText(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.Center),
                text = text,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }
    )
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PrimaryButtonPreview() {
    Box(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        PrimaryButton(
            text = "Hello world!".asUiString(),
        )
    }
}