package kz.grandera.vlifetesttaskapp.shared

import kotlin.native.HiddenFromObjC
import kotlin.experimental.ExperimentalObjCRefinement

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
internal class Greeting {
    fun helloWorld() {
        println("Hello World!")
    }
}