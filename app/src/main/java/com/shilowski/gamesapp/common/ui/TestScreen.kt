package com.shilowski.gamesapp.common.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.shilowski.gamesapp.common.domain.models.asUiString
import com.shilowski.gamesapp.common.ui.text.HeaderMainText

@Composable
fun TestScreen() {

    var isVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AnimateDottedText("Waiting")

//        Button(
//            onClick = { isVisible = !isVisible }
//        ) {
//            Text("Toggle")
//        }
//
//        AnimatedVisibility(
//            visible = isVisible,
////            enter = slideIn(),
////            exit = fadeOut()
//        ) {
//            Box(Modifier.size(300.dp).background(Color.Red))
//        }
    }
}

@Composable
fun AnimateDottedText(
    text: String,
    modifier: Modifier = Modifier,
    cycleDuration: Int = 2000
) {
    val transition = rememberInfiniteTransition(label = "Dots Transition")

    val visibleDotsCount = transition.animateValue(
        initialValue = 0,
        targetValue = 4,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = cycleDuration,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "Visible Dots Count"
    )

    // Display the text with dynamically changing dots based on the animation
    val dots = ".".repeat(visibleDotsCount.value) + "  ".repeat(4 - visibleDotsCount.value)

    HeaderMainText(
        modifier = modifier,
        text = (text + dots).asUiString(),
        textAlign = TextAlign.Center
    )
}
