package kz.grandera.vlifetesttaskapp.ui_components.snackbar

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope

import androidx.compose.runtime.RememberObserver
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState

public class SnackbarController(
    public val scope: CoroutineScope,
    private val snackbarHostState: SnackbarHostState
) : RememberObserver {
    private var snackbarJob: Job? = null

    init { cancelActiveJob() }

    override fun onRemembered() {
    }

    override fun onAbandoned() {
    }

    override fun onForgotten() {
        snackbarJob?.cancel()
        snackbarJob = null
    }

    public fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        if (snackbarJob == null) {
            snackbarJob = scope.launch {
                snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel,
                    duration = duration
                )
                cancelActiveJob()
            }
        } else {
            cancelActiveJob()
            snackbarJob = scope.launch {
                snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel
                )
                cancelActiveJob()
            }
        }
    }

    private fun cancelActiveJob() {
        snackbarJob?.let { job ->
            job.cancel()
            snackbarJob = Job()
        }
    }
}