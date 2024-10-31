package kz.grandera.vlifetesttaskapp.ui.modalbottomsheet

import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material3.SheetValue
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.foundation.layout.ColumnScope

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
@ExperimentalMaterial3Api
public fun <T : Any> ChildSlotModalBottomSheet(
    childSlot: Value<ChildSlot<*, T>>,
    onDismiss: () -> Unit,
    sheetContent: @Composable ColumnScope.(T) -> Unit,
    modifier: Modifier = Modifier,
    dragHandle: @Composable (() -> Unit)? = null,
    confirmValueChange: (SheetValue) -> Boolean = { true },
    skipPartiallyExpanded: Boolean = true
) {
    val sheetState = rememberModalBottomSheetState(
        confirmValueChange = confirmValueChange,
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val childSlotState by childSlot.subscribeAsState()
    val child = childSlotState.rememberBottomSheetChild {
        if (sheetState.isVisible) {
            sheetState.hide()
        }
    }

    child?.instance?.also {
        ModalBottomSheet(
            content = { sheetContent(it) },
            dragHandle = dragHandle,
            modifier = modifier,
            sheetState = sheetState,
            onDismissRequest = onDismiss
        )
    }
}

@Composable
private fun <C : Any, T : Any> ChildSlot<C, T>.rememberBottomSheetChild(onDismiss: suspend () -> Unit): Child.Created<C, T>? {
    var bottomSheetChild: Child.Created<C, T>? by remember { mutableStateOf(child) }

    LaunchedEffect(child) {
        if (child == null) {
            onDismiss()
            bottomSheetChild = null
        } else {
            bottomSheetChild = child
        }
    }

    return bottomSheetChild
}