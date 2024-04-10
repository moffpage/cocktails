package kz.grandera.vlifetesttaskapp.ui_components.textfield

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import dev.icerock.moko.resources.compose.stringResource

import kz.grandera.vlifetesttaskapp.ui_components.resources.UiComponentsStrings

@Composable
public fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String = stringResource(resource = UiComponentsStrings.search),
    onValueChange: (String) -> Unit,
    showTrailingContent: Boolean = text.isNotEmpty(),
    onTrailingContentClicked: (() -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    CompositionLocalProvider(LocalContentColor provides Color(color = 0xFF9E9E9E)) {
        TextField(
            modifier = modifier,
            value = text,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.h4,
            leadingContent = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            trailingContent = {
                if (showTrailingContent) {
                    Icon(
                        modifier = Modifier.clickable { onTrailingContentClicked?.invoke() },
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                }
            },
            placeholder = placeholder,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions
        )
    }
}