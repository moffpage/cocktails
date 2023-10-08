package kz.grandera.vlifetesttaskapp.shake

import android.content.Context
import android.hardware.SensorManager

import com.squareup.seismic.ShakeDetector as PlatformShakeDetector

import kz.grandera.vlifetesttaskapp.core.platform.PlatformContext

public actual class ShakeActionHandler public actual constructor(
    context: PlatformContext,
    detector: ShakeDetector
) {
    private val sensorManager: SensorManager

    init {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val shakeDetector = PlatformShakeDetector {
            detector.onShakeDetected()
        }
        shakeDetector.start(sensorManager, SensorManager.SENSOR_DELAY_GAME)
    }
}