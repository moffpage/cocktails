package kz.grandera.vlifetesttaskapp.ui.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar as MaterialSnackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.foundation.layout.padding

@Composable
public fun Snackbar(
    modifier: Modifier = Modifier,
    hostState: SnackbarHostState,
    action: (() -> Unit)? = null,
    actionOnNewLine: Boolean = false,
    backgroundColor: Color = MaterialTheme.colors.surface
) {
    SnackbarHost(
        modifier = modifier,
        hostState = hostState
    ) { snackbarData ->
        MaterialSnackbar(
            modifier = Modifier
                .padding(all = 8.dp),
            action = {
                snackbarData.actionLabel?.let { actionLabel ->
                    if (actionLabel.isNotEmpty()) {
                        TextButton(onClick = { action?.invoke() }) {
                            Text(
                                text = actionLabel,
                                style = MaterialTheme.typography.body1
                                    .copy(color = MaterialTheme.colors.primary)
                            )
                        }
                    }
                }
            },
            content = {
                Text(
                    text = snackbarData.message,
                    style = MaterialTheme.typography.h4
                )
            },
            shape = MaterialTheme.shapes.medium,
            elevation = 0.dp,
            contentColor = Color.White,
            backgroundColor = backgroundColor,
            actionOnNewLine = actionOnNewLine,
        )
    }
}