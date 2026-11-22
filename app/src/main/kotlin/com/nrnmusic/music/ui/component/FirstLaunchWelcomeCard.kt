/**
 * NrN Music Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.nrnmusic.music.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nrnmusic.music.R

@Composable
fun FirstLaunchWelcomeCard(
    onContinue: () -> Unit,
    onClose: () -> Unit = onContinue,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Surface(
        modifier = Modifier.fillMaxWidth().heightIn(max = screenHeight * 0.88f),
        shape = RoundedCornerShape(28.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        tonalElevation = 8.dp,
        shadowElevation = 16.dp,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(22.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("NrNMusic", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Text(stringResource(R.string.welcome_made_by), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                IconButton(onClick = onClose) {
                    Icon(
                        painter = painterResource(R.drawable.close),
                        contentDescription = stringResource(R.string.close),
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
            Column(
                modifier = Modifier.weight(1f, fill = false).verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(stringResource(R.string.welcome_whats_new), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Text(stringResource(R.string.welcome_feature_recommendations), style = MaterialTheme.typography.bodyMedium)
                Text(stringResource(R.string.welcome_feature_recap), style = MaterialTheme.typography.bodyMedium)

                Spacer(Modifier.height(4.dp))
                Text(stringResource(R.string.welcome_features_title), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Text(stringResource(R.string.welcome_together_title), style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Text(stringResource(R.string.welcome_together_location), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 10.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TopBarPreviewIcon(R.drawable.history)
                    TopBarPreviewIcon(R.drawable.stats)
                    TopBarPreviewIcon(R.drawable.group_outlined, selected = true)
                    TopBarPreviewIcon(R.drawable.account)
                }

                Text(stringResource(R.string.welcome_together_steps), style = MaterialTheme.typography.bodyMedium)
                Text(stringResource(R.string.welcome_together_extras), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)

                Spacer(Modifier.height(4.dp))
                Text(stringResource(R.string.welcome_hidden_title), style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Text(stringResource(R.string.welcome_hidden_sleep), style = MaterialTheme.typography.bodyMedium)
                Text(stringResource(R.string.welcome_hidden_equalizer), style = MaterialTheme.typography.bodyMedium)
                Text(stringResource(R.string.welcome_hidden_radio), style = MaterialTheme.typography.bodyMedium)
                Text(stringResource(R.string.welcome_hidden_lyrics), style = MaterialTheme.typography.bodyMedium)
                Text(stringResource(R.string.welcome_hidden_wrapped), style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(Modifier.height(14.dp))
            Button(onClick = onContinue, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.welcome_continue))
            }
        }
    }
}

@Composable
private fun TopBarPreviewIcon(
    drawableRes: Int,
    selected: Boolean = false,
) {
    Surface(
        shape = CircleShape,
        color = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Icon(
            painter = painterResource(drawableRes),
            contentDescription = null,
            modifier = Modifier.padding(10.dp).size(24.dp),
            tint = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
