package kz.grandera.vlifetesttaskapp.component

import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable

public interface Component {
    @Composable
    public fun Content(modifier: Modifier = Modifier)
}