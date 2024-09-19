package com.shilowski.gamesapp.room.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shilowski.gamesapp.R
import com.shilowski.gamesapp.common.domain.models.asUiString
import com.shilowski.gamesapp.common.ui.buttons.PrimaryButton
import com.shilowski.gamesapp.common.ui.loading.LoadingText
import com.shilowski.gamesapp.common.ui.text.HeaderMainText
import com.shilowski.gamesapp.common.ui.text.SmallText
import com.shilowski.gamesapp.common.ui.theme.Aquamarine
import com.shilowski.gamesapp.common.ui.theme.BoogerBuster
import com.shilowski.gamesapp.common.ui.theme.BrightLilac
import com.shilowski.gamesapp.common.ui.theme.Crayola
import com.shilowski.gamesapp.common.ui.theme.FrenchSkyBlue
import com.shilowski.gamesapp.common.ui.theme.LightTaupe
import com.shilowski.gamesapp.common.ui.theme.Salmon
import com.shilowski.gamesapp.common.ui.theme.SilverSand

// todo: add state

private const val HEADER_KEY = "Header"
private const val SECONDARY_HEADER_KEY = "Header2"
private const val CONTENT_KEY = "content"
private const val FOOTER_KEY = "Footer"

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RoomScreen(navController: NavController) {
    val isRoomOwner = false

    // todo: hardcoded value
    val testUsers =
        //listOf("Mike", "Lida", "Ivan", "Sahra", "Yahn", "Name", "Hello", "World")
        emptyList<String>()

    val possibleColors = listOf(
        Salmon,
        FrenchSkyBlue,
        BoogerBuster,
        Crayola,
        SilverSand,
        LightTaupe,
        BrightLilac,
        Aquamarine
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        userScrollEnabled = true,
        // todo: how does it work?? / simplify!!
        verticalArrangement = remember {
            object : Arrangement.Vertical {
                override fun Density.arrange(
                    totalSize: Int,
                    sizes: IntArray,
                    outPositions: IntArray
                ) {
                    var currentOffset = 0
                    sizes.forEachIndexed { index, size ->
                        if (index == sizes.lastIndex) {
                            outPositions[index] = totalSize - size
                        } else {
                            outPositions[index] = currentOffset
                            currentOffset += size
                        }
                    }
                }
            }
        }
    ) {
        item(key = HEADER_KEY) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // todo: on click show dialog with game rules
                Image(
                    modifier = Modifier.size(34.dp),
                    painter = painterResource(id = R.drawable.ic_info),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    contentDescription = "Info"
                )
                HeaderMainText(
                    modifier = Modifier.width(300.dp),
                    text = "Cards Against Humanity".asUiString(),
                    maxLines = 3,
                    textAlign = TextAlign.Center
                )
                // todo: on click show exit dialog with explainer
                Image(
                    modifier = Modifier.size(34.dp),
                    painter = painterResource(id = R.drawable.ic_close),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    contentDescription = "Exit"
                )
            }
        }

        item(key = SECONDARY_HEADER_KEY) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                SmallText(
                    modifier = Modifier.padding(bottom = 50.dp),
                    text = "Your room code is".asUiString()
                )

                HeaderMainText(
                    text = "XYZM".asUiString(),
                    fontSize = 42.sp,
                )
            }
        }

        item(key = CONTENT_KEY) {
            // if isRoomOwner and no users are shown -> use loading animation
            if (isRoomOwner && testUsers.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize().height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
            } else {
                FlowRow(horizontalArrangement = Arrangement.Center) {
                    testUsers.forEachIndexed { index, item ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp, vertical = 4.dp)
                                .background(MaterialTheme.colorScheme.surface)
                                .border(3.dp, MaterialTheme.colorScheme.onBackground)
                                .padding(16.dp)
                        ) {
                            Image(
                                modifier = Modifier.size(22.dp),
                                painter = painterResource(id = R.drawable.ic_user),
                                colorFilter = ColorFilter.tint(possibleColors[index]),
                                contentDescription = "Info"
                            )
                            Text(
                                text = item,
                                modifier = Modifier
                                    .padding(start = 28.dp)
                                    .align(Alignment.Center),
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        item(key = FOOTER_KEY) {
            // show only if roomOwner
            // disable if not enough players joined
            if (isRoomOwner) {
                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    text = "Start Game".asUiString(), // fixme: hardcoded
                )
            } else {
                LoadingText(
                    text = "Waiting".asUiString(),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}