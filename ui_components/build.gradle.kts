plugins {
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
            api(dependencyNotation = libs.coil.compose)
            api(dependencyNotation = libs.bundles.compose)
            api(dependencyNotation = libs.compottie)

            implementation(dependencyNotation = libs.compose.tooling)
        }

        iosMain {
            dependsOn(commonMain.get())

            iosX64 { dependsOn(this@iosMain) }
            iosArm64 { dependsOn(this@iosMain) }
            iosSimulatorArm64 { dependsOn(this@iosMain) }

            dependencies {
                implementation(dependencyNotation = libs.coil.network.ktor)
            }
        }

        androidMain {
            dependsOn(commonMain.get())
            dependencies {
                implementation(dependencyNotation = libs.coil.network.okhttp)
            }
        }
    }
}

compose.resources {
    publicResClass = true
    generateResClass = always
    packageOfResClass = "kz.grandera.vlifetesttaskapp.ui_components"
}