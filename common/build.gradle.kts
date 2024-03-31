import dev.icerock.gradle.MRVisibility

plugins {
    kotlin(module = "multiplatform")

    id("com.android.library")

    id("dev.icerock.mobile.multiplatform-resources")

    kotlin(module = "plugin.serialization")
}

android {
    namespace = "kz.grandera.vlifetesttaskapp"
    compileSdk = 34

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets.getByName("main").res.srcDir(File(buildDir, "generated/moko/androidMain/res"))
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
                implementation(dependencyNotation = project(path = ":core"))
                implementation(dependencyNotation = project(path = ":ui_components"))

                implementation(dependencyNotation = libs.koin.core)
                implementation(dependencyNotation = libs.ktor.core)
                implementation(dependencyNotation = libs.moko.resources.common)
                implementation(dependencyNotation = libs.kotlinx.coroutines)
                implementation(dependencyNotation = libs.mvikotlin.logging)
                implementation(dependencyNotation = libs.bundles.mvikotlin.common)
                implementation(dependencyNotation = libs.decompose.core)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(dependencyNotation = libs.coil.compose)
                implementation(dependencyNotation = libs.moko.resources.compose)
                implementation(dependencyNotation = libs.lottie.compose)
                implementation(dependencyNotation = libs.bundles.compose)
                implementation(dependencyNotation = libs.decompose.extensions.compose.jetpack)
            }
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "kz.grandera.vlifetesttaskapp.common"
    multiplatformResourcesClassName = "CommonRes"
    multiplatformResourcesVisibility = MRVisibility.Internal
}