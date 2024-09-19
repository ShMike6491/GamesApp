package com.shilowski.gamesapp.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shilowski.gamesapp.R
import com.shilowski.gamesapp.common.domain.models.asUiString
import com.shilowski.gamesapp.common.domain.navigation.Screen
import com.shilowski.gamesapp.common.ui.buttons.PrimaryButton
import com.shilowski.gamesapp.common.ui.input.PrimaryTextInput
import com.shilowski.gamesapp.common.ui.text.HeaderMainText
import com.shilowski.gamesapp.common.ui.text.SmallText

@Composable
fun CreateGameDialog(navController: NavController) {
    val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.End),
//                .clickable { dialogState.value = false },
            painter = painterResource(id = R.drawable.ic_close),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            contentDescription = "Close dialog"
        )

        HeaderMainText(
            text = "Create game".asUiString(),
            fontSize = 24.sp
        )

        SmallText(text = loremIpsum.asUiString())

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            modifier = Modifier.align(Alignment.End)
                .height(48.dp),
            text = "Start".asUiString(),
            onClick = { navController.navigate(Screen.Room.route) }
        )
    }
}