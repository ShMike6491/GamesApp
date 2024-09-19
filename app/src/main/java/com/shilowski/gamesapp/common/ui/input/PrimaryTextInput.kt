package com.shilowski.gamesapp.common.ui.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shilowski.gamesapp.common.domain.models.UiString
import com.shilowski.gamesapp.common.ui.text.HeaderMainText

@Composable
fun PrimaryTextInput(
    modifier: Modifier = Modifier,
    hint: UiString,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    // fixme: app crash when user deletes previous text and starts typing
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.surface),
        textStyle = MaterialTheme.typography.headlineLarge.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .border(3.dp, MaterialTheme.colorScheme.onBackground)
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                if (value.text.isBlank()) {
                    HeaderMainText(
                        text = hint,
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                } else {
                    innerTextField()
                }
            }
        }
    )
}