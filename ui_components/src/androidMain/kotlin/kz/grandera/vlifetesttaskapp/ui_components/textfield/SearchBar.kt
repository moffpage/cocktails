package kz.grandera.vlifetesttaskapp.ui_components.textfield

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import org.jetbrains.compose.ui.tooling.preview.Preview

import kz.grandera.vlifetesttaskapp.ui_components.R
import kz.grandera.vlifetesttaskapp.ui_components.resources.UiComponentsStrings
import kz.grandera.vlifetesttaskapp.ui_components.theming.VlifeTestTaskAppTheme

@Composable
public fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String = stringResource(id = UiComponentsStrings.search.resourceId),
    onValueChange: (String) -> Unit,
    showTrailingContent: Boolean = text.isNotEmpty(),
    onTrailingContentClicked: (() -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
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
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = null
                )
            }
        },
        placeholder = placeholder,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions
    )
}

@Preview
@Composable
private fun LightSearchBarPreview() {
    VlifeTestTaskAppTheme(applyDarkTheme = false) {
        SearchBar(
            modifier = Modifier.height(height = 36.dp),
            text = "Martini",
            onValueChange = { }
        )
    }
}

@Preview
@Composable
private fun SearchBarDarkPreview() {
    VlifeTestTaskAppTheme(applyDarkTheme = true) {
        SearchBar(
            modifier = Modifier.height(height = 36.dp),
            text = "Martini",
            onValueChange = { }
        )
    }
}