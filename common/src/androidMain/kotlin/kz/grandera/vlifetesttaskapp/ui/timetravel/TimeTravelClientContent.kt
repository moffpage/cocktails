package kz.grandera.vlifetesttaskapp.ui.timetravel

import android.view.ViewGroup

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material.SnackbarHostState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.rememberLauncherForActivityResult

import com.arkivanov.mvikotlin.timetravel.widget.TimeTravelView

import kz.grandera.vlifetesttaskapp.ui.common.Snackbar
import kz.grandera.vlifetesttaskapp.utils.SnackbarController
import kz.grandera.vlifetesttaskapp.features.timetravel.component.TimeTravelClientComponent
import kz.grandera.vlifetesttaskapp.features.timetravel.component.TimeTravelClientComponent.Event

@Composable
internal fun TimeTravelClientContent(component: TimeTravelClientComponent) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarController = remember(scope, snackbarHostState) {
        SnackbarController(
            scope = scope,
            snackbarHostState = snackbarHostState
        )
    }

    val openFilesIntentResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            component.importEvents(uri = uri)
        }
    }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        component.events.collect { event ->
            when (event) {
                is Event.ErrorOccurred -> {
                    snackbarController.showSnackbar(
                        message = context.getString(
                            event.message.resourceId
                        )
                    )
                }
            }
        }
    }

    Box(modifier = Modifier.systemBarsPadding()) {
        AndroidView(
            factory = { context ->
                TimeTravelView(context = context)
                    .apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        setOnExportEventsListener { component.exportEvents() }
                        setOnImportEventsListener {
                            openFilesIntentResult.launch(arrayOf("text/*"))
                        }
                    }
            }
        )
        
        Snackbar(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .padding(bottom = 48.dp),
            hostState = snackbarHostState
        )
    }
}