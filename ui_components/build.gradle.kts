import dev.icerock.gradle.MRVisibility
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.moko.resources.generator)
    id(libs.plugins.android.library.get().pluginId)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.kotlin)
    alias(libs.plugins.multiplatform)
}

android {
    namespace = "kz.grandera.vlifetesttaskapp.ui_components"
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
        commonMain.dependencies {
            implementation(dependencyNotation = libs.moko.resources.common)
            implementation(dependencyNotation = libs.compose.runtime)
        }

        androidMain.dependencies {
            api(dependencyNotation = libs.moko.resources.compose)
            api(dependencyNotation = libs.coil.compose)
            api(dependencyNotation = libs.lottie.compose)
            api(dependencyNotation = libs.bundles.compose)
            api(dependencyNotation = libs.compose.tooling)
        }
    }
}

multiplatformResources {
    resourcesPackage.set("kz.grandera.vlifetesttaskapp.ui_components")
    resourcesClassName.set("UiComponentsRes")
    resourcesVisibility.set(MRVisibility.Internal)
}