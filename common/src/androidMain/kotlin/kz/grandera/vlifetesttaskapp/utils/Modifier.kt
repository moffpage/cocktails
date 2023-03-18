package kz.grandera.vlifetesttaskapp.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.PointerEventPass

internal fun Modifier.disableInput(allow: Boolean = false): Modifier =
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