/**
 * NrN Music Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.nrnmusic.music.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.nrnmusic.music.R
import com.nrnmusic.music.ui.component.FirstLaunchWelcomeCard

@Composable
fun AboutScreen(navController: NavController) {
    var showWelcomeCard by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Button(
            onClick = { showWelcomeCard = true },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("NrNMusic")
        }
        Spacer(Modifier.height(4.dp))
        Text(stringResource(R.string.about_version_2), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.about_release_summary),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }

    if (showWelcomeCard) {
        Dialog(onDismissRequest = { showWelcomeCard = false }) {
            FirstLaunchWelcomeCard(
                onContinue = { showWelcomeCard = false },
                onClose = { showWelcomeCard = false },
            )
        }
    }
}
