package com.shilowski.gamesapp.common.ui.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shilowski.gamesapp.common.domain.models.UiString
import com.shilowski.gamesapp.common.domain.models.asUiString
import com.shilowski.gamesapp.common.ui.text.HeaderMainText


@Composable
fun LoadingText(
    modifier: Modifier = Modifier,
    text: UiString,
    duration: Int = 2000,
) {
    val transition = rememberInfiniteTransition(label = "Dots Transition")

    val visibleDotsCount = transition.animateValue(
        initialValue = 0,
        targetValue = 4,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = duration,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "Visible Dots Count"
    )

    // Display the text with dynamically changing dots based on the animation
    val dots = ".".repeat(visibleDotsCount.value)

    HeaderMainText(
        modifier = modifier,
        text = (text.asString() + dots).asUiString(),
        textAlign = TextAlign.Center
    )
}