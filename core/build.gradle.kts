plugins {
    kotlin(module = "multiplatform")

    id("com.android.library")
    id("kotlin-parcelize")

    kotlin(module = "plugin.serialization")
}

android {
    namespace = "kz.grandera.vlifetesttaskapp.core"
    compileSdk = 21

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

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "common"
            transitiveExport = true
            export(dependency = libs.reaktive)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(dependencyNotation = libs.napier)
                api(dependencyNotation = libs.reaktive)

                implementation(dependencyNotation = libs.ktor.core)
                implementation(dependencyNotation = libs.ktor.logging)
                implementation(dependencyNotation = libs.ktor.serialization.json)
                implementation(dependencyNotation = libs.ktor.contentnegotiation)
                implementation(dependencyNotation = libs.moko.resources.common)
                implementation(dependencyNotation = libs.klock)
                implementation(dependencyNotation = libs.koin3.core)
                implementation(dependencyNotation = libs.essenty.lifecycle)
                implementation(dependencyNotation = libs.decompose.core)
                implementation(dependencyNotation = libs.mvikotlin.rx)
                implementation(dependencyNotation = libs.mvikotlin.core)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(dependencyNotation = libs.ktor.engine.okhttp)
                implementation(dependencyNotation = libs.android.core)
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