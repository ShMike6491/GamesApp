package com.shilowski.gamesapp.common.ui.containers

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    strokeWidth: Dp = 4.dp,
    strokeColor: Color = MaterialTheme.colorScheme.onBackground,
    showShadow: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) {
    val halfStrokeWidth = strokeWidth.value.div(2).dp
    val boxBorderWidth = (strokeWidth.value - (strokeWidth.value * 0.25f)).dp
    val shadowModifier = if (showShadow) {
        Modifier
            .drawBehind {
                drawLine(
                    color = strokeColor,
                    start = Offset(strokeWidth.toPx(), size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = strokeWidth.toPx()
                )
                drawLine(
                    color = strokeColor,
                    start = Offset(size.width, strokeWidth.toPx()),
                    end = Offset(size.width, (size.height + halfStrokeWidth.toPx())),
                    strokeWidth = halfStrokeWidth.toPx()
                )
            }
            .padding(bottom = halfStrokeWidth, end = halfStrokeWidth / 2)
    } else {
        Modifier
    }

    Box(modifier = modifier) {
        Box(
            modifier = shadowModifier
                .background(backgroundColor)
                .border(boxBorderWidth, strokeColor)
        ) {
            content()
        }
    }
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
fun PrimaryBoxPreview() {
    Box(Modifier.background(MaterialTheme.colorScheme.secondary).padding(16.dp)) {
        PrimaryBox(
            Modifier.align(Alignment.Center)
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center),
                text = "Hello world!"
            )
        }
    }
}