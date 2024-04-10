package kz.grandera.vlifetesttaskapp.ios

import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

import platform.objc.sel_registerName
import platform.darwin.NSObject
import platform.Foundation.NSNotificationCenter

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.subscribe

public fun ShakeDetectorFactory(lifecycle: Lifecycle): ShakeDetector = ShakeDetector(lifecycle = lifecycle)

@OptIn(
    BetaInteropApi::class,
    ExperimentalForeignApi::class
)
public class ShakeDetector(lifecycle: Lifecycle) : NSObject() {
    private val coroutineScope = CoroutineScope(context = Dispatchers.Main.immediate)

    private val _shakeEvents: MutableSharedFlow<Unit> = MutableSharedFlow()
    public val shakeEvents: Flow<Unit> = _shakeEvents

    init {
        lifecycle.subscribe(
            onCreate = {
                NSNotificationCenter.defaultCenter.addObserver(
                    observer = this,
                    name = "deviceDidShakeNotification",
                    selector = sel_registerName("handleDeviceShakeEvent"),
                    `object` = null
                )
            },
            onDestroy = {
                NSNotificationCenter.defaultCenter.removeObserver(
                    observer = this
                )
            }
        )
    }

    @ObjCAction
    private fun handleDeviceShakeEvent() {
        coroutineScope.launch {
            _shakeEvents.emit(value = Unit)
        }
    }
}