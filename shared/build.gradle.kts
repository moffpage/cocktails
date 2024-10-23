import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.kotlin)
    alias(libs.plugins.multiplatform)
}

android {
    namespace = "kz.grandera.vlifetesttaskapp.shared"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        applicationId = "kz.grandera.vlifetesttaskapp"
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    applyDefaultHierarchyTemplate()

    androidTarget {
        compilations.all {
            compileTaskProvider {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            export(dependency = libs.essenty.lifecycle)
            export(dependency = libs.essenty.backhandler)
            export(dependency = libs.decompose.core)
            export(dependency = project(":core"))
            export(dependency = project(":common"))
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api(dependencyNotation = project(path = ":core"))
                api(dependencyNotation = project(path = ":common"))

                api(dependencyNotation = libs.decompose.core)

                implementation(dependencyNotation = project(path = ":ui_components"))
            }
        }

        androidMain.dependencies {
            implementation(dependencyNotation = libs.koin.android)
            implementation(dependencyNotation = libs.android.activity.compose)
            implementation(dependencyNotation = libs.seismic)
        }
    }
}