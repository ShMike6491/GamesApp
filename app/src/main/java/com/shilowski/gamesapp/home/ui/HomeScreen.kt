package com.shilowski.gamesapp.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shilowski.gamesapp.R
import com.shilowski.gamesapp.common.domain.models.asUiString
import com.shilowski.gamesapp.common.domain.navigation.Screen
import com.shilowski.gamesapp.common.ui.buttons.PrimaryButton
import com.shilowski.gamesapp.common.ui.dialogs.PrimaryAlertDialog
import com.shilowski.gamesapp.common.ui.text.SmallText

@Composable
fun HomeScreen(
    navController: NavController
    // todo: add state model
) {
    val context = LocalContext.current
    val input = remember { mutableStateOf(TextFieldValue("My Name")) }

    // todo: show error message if no name was provided
    // todo: we need to change the buttons and description if there are no permissions granted for notifications
    val permissionText = "To enable game controls and receive important updates, please tap 'Allow' for notification permissions. This ensures a smooth gaming experience, keeping you in sync with the action. Let's make your gameplay seamless – just a quick tap away!"
    val nameErrorText = "Oops! Please provide a valid name to continue. Your name should only contain letters or numbers, and no spaces."
    val generatedText = "This game is designed for local play, fostering real-life connections by requiring all participants to be on the same wifi network. Gather in a shared space, connect to the same wifi, and enjoy a collaborative gaming experience in person. \n\nCreate your own game or join others by entering the room code after pressing 'Join Game'. Make the most of your gaming night by choosing your favorite board game and inviting others to join the fun!"

    // todo: move out to state
    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    if (dialogState.value) {
        PrimaryAlertDialog(
            onDismissAction = { dialogState.value = false },
            content =  { JoinDialog(navController) }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 34.dp)
    ) {

        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = input.value,
            onValueChange = { input.value = it },
            maxLines = 2,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.background),
            textStyle = MaterialTheme.typography.headlineLarge.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        )

        Box(
            modifier = Modifier
                .height(150.dp)
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            SmallText(text = generatedText.asUiString())
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = "New game".asUiString(), // fixme: hardcoded
                onClick = { navController.navigate(Screen.SelectGame.route) }
            )

            Spacer(Modifier.height(16.dp))

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Join game".asUiString(), // fixme: hardcoded
                onClick = { dialogState.value = true }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(34.dp), // fixme: hardcoded
                painter = painterResource(id = R.drawable.ic_mail),
                contentDescription = "Contact us",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )

            Spacer(modifier = Modifier.width(32.dp))

            Image(
                modifier = Modifier.size(34.dp),
                painter = painterResource(id = R.drawable.ic_policy),
                contentDescription = "Privacy terms",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )

            Spacer(modifier = Modifier.width(32.dp))

            Image(
                modifier = Modifier.size(34.dp), // fixme: hardcoded
                painter = painterResource(id = R.drawable.ic_care),
                contentDescription = "Support us",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }
    }
}