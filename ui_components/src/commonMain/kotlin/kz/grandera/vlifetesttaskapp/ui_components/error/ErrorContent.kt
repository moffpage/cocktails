package kz.grandera.vlifetesttaskapp.ui_components.error

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.PaddingValues

import org.jetbrains.compose.resources.stringResource

import kz.grandera.vlifetesttaskapp.ui_components.Res
import kz.grandera.vlifetesttaskapp.ui_components.retry
import kz.grandera.vlifetesttaskapp.ui_components.error_occurred
import kz.grandera.vlifetesttaskapp.ui_components.resources.Icons
import kz.grandera.vlifetesttaskapp.ui_components.resources.icons.Error

@Composable
public fun ErrorContent(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = 32.dp,
                    horizontal = 88.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val errorText = stringResource(
                resource = Res.string.error_occurred
            )
            Image(
                modifier = Modifier.size(size = 48.dp),
                imageVector = Icons.Error,
                contentDescription = errorText
            )
            Spacer(modifier = Modifier.height(height = 16.dp))
            Text(
                text = errorText,
                style = MaterialTheme.typography.h3
                    .copy(textAlign = TextAlign.Center)
            )
            Spacer(modifier = Modifier.height(height = 16.dp))
            Button(
                onClick = onRetry,
                elevation = null,
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 66.dp
                )
            ) {
                Text(
                    text = stringResource(resource = Res.string.retry),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}