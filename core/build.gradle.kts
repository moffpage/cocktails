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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin {
    explicitApiWarning()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
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
        }

        val androidMain by getting {
            dependencies {
                implementation(dependencyNotation = libs.ktor.engine.okhttp)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(dependencyNotation = libs.ktor.engine.darwin)
            }
        }
    }
}