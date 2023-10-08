package kz.grandera.vlifetesttaskapp.time_travel_client.component

import com.arkivanov.decompose.ComponentContext

public fun timeTravelComponentFactory(
    componentContext: ComponentContext,
    onNavigateBack: () -> Unit
): TimeTravelClientComponent =
    TimeTravelClientComponentImpl(
        componentContext = componentContext,
        onNavigateBack = onNavigateBack
    )