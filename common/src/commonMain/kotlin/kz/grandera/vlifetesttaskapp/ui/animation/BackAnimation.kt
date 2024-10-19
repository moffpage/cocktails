package kz.grandera.vlifetesttaskapp.ui.animation

import com.arkivanov.essenty.backhandler.BackHandler
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.experimental.stack.animation.StackAnimation

@ExperimentalDecomposeApi
public expect fun <C : Any, T : Any> backAnimation(
    backHandler: BackHandler,
    onBack: () -> Unit
): StackAnimation<C, T>
