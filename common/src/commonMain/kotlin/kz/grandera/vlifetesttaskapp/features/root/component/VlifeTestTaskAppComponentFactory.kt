package kz.grandera.vlifetesttaskapp.features.root.component

import com.arkivanov.decompose.ComponentContext

public fun vlifeTestTaskAppComponent(componentContext: ComponentContext): VlifeTestTaskAppComponent =
    VlifeTestTaskAppComponentImpl(componentContext = componentContext)