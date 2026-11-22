/**
 * NrN Music Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.nrnmusic.music.ui.screens.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.nrnmusic.music.R
import com.nrnmusic.music.ui.component.IconButton
import com.nrnmusic.music.ui.utils.backToMain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdaterScreen(navController: NavController) {
    TopAppBar(
        title = { Text(stringResource(R.string.updater)) },
        navigationIcon = {
            IconButton(
                onClick = navController::navigateUp,
                onLongClick = navController::backToMain,
            ) {
                Icon(painter = painterResource(R.drawable.arrow_back), contentDescription = null)
            }
        },
    )
}
