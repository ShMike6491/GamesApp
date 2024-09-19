package com.shilowski.gamesapp.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shilowski.gamesapp.R
import com.shilowski.gamesapp.common.domain.models.asUiString
import com.shilowski.gamesapp.common.ui.buttons.PrimaryButton
import com.shilowski.gamesapp.common.ui.dialogs.PrimaryAlertDialog
import com.shilowski.gamesapp.common.ui.text.HeaderMainText
import com.shilowski.gamesapp.common.ui.text.SmallText

// as for now we will only have one game, so there's no need in this screen
// but soon there will be more functionality and we will have to develop this

@Composable
fun SelectGameScreen(
    navController: NavController
    // todo: add state model
) {
    val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

    val games = listOf("Uno", "TicTacToy", "Cards Against Humanity", "Mafia", "Imaginarium")

    // todo: move out to state
    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    if (dialogState.value) {
        PrimaryAlertDialog(
            onDismissAction = { dialogState.value = false },
            content =  { CreateGameDialog(navController) }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Image(
                modifier = Modifier.size(34.dp),
                painter = painterResource(id = R.drawable.ic_left_arrow),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                contentDescription = "Navigate up"
            )
            HeaderMainText(text = "Select game".asUiString())
            Spacer(Modifier.size(34.dp))
        }

        SmallText(
            modifier = Modifier.padding(vertical = 16.dp),
            text = loremIpsum.asUiString()
        )

        LazyColumn(Modifier.fillMaxWidth()) {
            items(games) { item ->
                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    text = item.asUiString(),
                    onClick = { dialogState.value = true }
                )
            }
        }
    }
}