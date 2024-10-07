import dev.icerock.gradle.MRVisibility
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.moko.resources.generator)
    id(libs.plugins.android.library.get().pluginId)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.kotlin)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.multiplatform)
}

android {
    namespace = "kz.grandera.vlifetesttaskapp"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    sourceSets.getByName("main").res.srcDir(File(buildDir, "generated/moko/androidMain/res"))
}

kotlin {
    explicitApiWarning()
    applyDefaultHierarchyTemplate()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    androidTarget {
        compilations.all {
            compileTaskProvider {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCRefinement")
        }

        commonMain.dependencies {
            implementation(dependencyNotation = project(path = ":core"))
            implementation(dependencyNotation = project(path = ":ui_components"))

            implementation(dependencyNotation = libs.koin.core)
            implementation(dependencyNotation = libs.ktor.core)
            implementation(dependencyNotation = libs.moko.resources.common)
            implementation(dependencyNotation = libs.kotlinx.coroutines)
            implementation(dependencyNotation = libs.bundles.mvikotlin.common)
            implementation(dependencyNotation = libs.mvikotlin.logging)
            implementation(dependencyNotation = libs.decompose.core)
        }

        androidMain.dependencies {
            implementation(dependencyNotation = libs.decompose.extensions.compose)
        }
    }
}

multiplatformResources {
    resourcesPackage.set("kz.grandera.vlifetesttaskapp.common")
    resourcesClassName.set("CommonRes")
    resourcesVisibility.set(MRVisibility.Internal)
}