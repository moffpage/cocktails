package kz.grandera.vlifetesttaskapp.di.module

import org.koin.dsl.module
import org.koin.core.module.Module

import kz.grandera.vlifetesttaskapp.shake.ShakeActionHandler

//public val shakeModule: Module = module {
//    single { parameters ->
//        ShakeActionHandler(
//            context = get(),
//            detector = parameters.get()
//        )
//    }
//}