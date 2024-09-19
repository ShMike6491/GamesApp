package com.shilowski.gamesapp.common.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.asPx(): Float = LocalDensity.current.run { this@asPx.toPx() }

@Composable
fun Float.asDp(): Dp = LocalDensity.current.run { this@asDp.toDp() }