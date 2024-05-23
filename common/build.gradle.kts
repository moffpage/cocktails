plugins {
    id(libs.plugins.android.library.get().pluginId)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.kotlin)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.multiplatform)
}

android {
    namespace = "kz.grandera.vlifetesttaskapp.common"
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
}

kotlin {
    explicitApiWarning()
    applyDefaultHierarchyTemplate()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(dependencyNotation = libs.decompose.extensions.compose)

            implementation(dependencyNotation = project(path = ":core"))
            implementation(dependencyNotation = project(path = ":ui_components"))

            implementation(dependencyNotation = libs.koin.core)
            implementation(dependencyNotation = libs.ktor.core)
            implementation(dependencyNotation = libs.kotlinx.coroutines)
            implementation(dependencyNotation = libs.bundles.mvikotlin.common)
            implementation(dependencyNotation = libs.mvikotlin.logging)
            implementation(dependencyNotation = libs.decompose.core)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "kz.grandera.vlifetesttaskapp.common"
    generateResClass = always
}