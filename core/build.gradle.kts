import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id(libs.plugins.android.library.get().pluginId)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.multiplatform)
}

android {
    namespace = "kz.grandera.vlifetesttaskapp.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }

    buildFeatures {
        buildConfig = true
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
            compileTaskProvider {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(dependencyNotation = libs.napier)

            implementation(dependencyNotation = libs.koin.core)
            implementation(dependencyNotation = libs.ktor.core)
            implementation(dependencyNotation = libs.ktor.logging)
            implementation(dependencyNotation = libs.ktor.serialization.json)
            implementation(dependencyNotation = libs.ktor.contentnegotiation)
            implementation(dependencyNotation = libs.essenty.lifecycle)
            implementation(dependencyNotation = libs.decompose.core)
            implementation(dependencyNotation = libs.mvikotlin.core)
        }

        androidMain.dependencies {
            implementation(dependencyNotation = libs.ktor.engine.okhttp)
        }

        iosMain {
            iosX64 { dependsOn(this@iosMain) }
            iosArm64 { dependsOn(this@iosMain) }
            iosSimulatorArm64 { dependsOn(this@iosMain) }

            dependencies {
                implementation(dependencyNotation = libs.ktor.engine.darwin)
            }
        }
    }
}