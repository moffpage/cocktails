package kz.grandera.vlifetesttaskapp.ui_components.textfield

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.background
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.interaction.MutableInteractionSource

@Composable
public fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String?,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    textStyle: TextStyle = LocalTextStyle.current,
    maxLines: Int = Int.MAX_VALUE,
    enabled: Boolean = true,
    isError: Boolean = false,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    placeholderColor: Color = contentColorFor(backgroundColor = backgroundColor),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val colors = TextFieldDefaults.colors(
        focusedTextColor = textColor,
        unfocusedTextColor = textColor,
        focusedContainerColor = backgroundColor,
        unfocusedContainerColor = backgroundColor,
        focusedPlaceholderColor = placeholderColor,
        unfocusedLabelColor = placeholderColor,
        errorIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
    )

    BasicTextField(
        enabled = enabled,
        modifier = modifier
            .background(
                color = colors.focusedContainerColor,
                shape = shape
            )
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle.merge(other = TextStyle(color = textColor)),
        cursorBrush = SolidColor(value = colors.cursorColor),
        decorationBox = @Composable { innerTextField ->
            @OptIn(ExperimentalMaterial3Api::class)
            TextFieldDefaults.DecorationBox(
                value = value,
                placeholder = {
                    if (placeholder != null) {
                        Text(
                            text = placeholder,
                            style = textStyle
                                .copy(color = placeholderColor)
                        )
                    }
                },
                colors = colors,
                isError = isError,
                enabled = enabled,
                singleLine = singleLine,
                leadingIcon = leadingContent,
                trailingIcon = trailingContent,
                innerTextField = innerTextField,
                contentPadding = PaddingValues(all = 8.dp),
                interactionSource = remember { MutableInteractionSource() },
                visualTransformation = visualTransformation,
            )
        },
        maxLines = maxLines,
        readOnly = readOnly,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation
    )
}