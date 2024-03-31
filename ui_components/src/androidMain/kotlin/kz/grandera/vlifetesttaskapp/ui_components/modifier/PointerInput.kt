package kz.grandera.vlifetesttaskapp.ui_components.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.PointerEventPass

public fun Modifier.disableInput(allow: Boolean = false): Modifier =
    if (!allow) {
        this.pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    val event = awaitPointerEvent(
                        pass = PointerEventPass.Initial
                    )
                    event.changes.forEach { pointerInputChange ->
                        pointerInputChange.consume()
                    }
                }
            }
        }
    } else { this }