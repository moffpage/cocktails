import dev.icerock.gradle.MRVisibility

plugins {
    kotlin(module = "multiplatform")

    id("com.android.library")
    id("kotlin-parcelize")
    id("dev.icerock.mobile.multiplatform-resources")

    kotlin(module = "plugin.serialization")
}

android {
    namespace = "kz.grandera.vlifetesttaskapp"
    compileSdk = 21

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
            export(dependency = libs.moko.resources.common)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(dependencyNotation = libs.moko.resources.common)
                api(dependencyNotation = libs.decompose.core)

                implementation(dependencyNotation = project(path = ":core"))

                implementation(dependencyNotation = libs.ktor.core)
                implementation(dependencyNotation = libs.koin3.core)
                implementation(dependencyNotation = libs.kotlinx.coroutines)
                implementation(dependencyNotation = libs.kotlinx.serialization)
                implementation(dependencyNotation = libs.bundles.mvikotlin.common)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(dependencyNotation = project(path = ":ui_components"))

                implementation(dependencyNotation = libs.coil.compose)
                implementation(dependencyNotation = libs.moko.resources.compose)
                implementation(dependencyNotation = libs.lottie.compose)
                implementation(dependencyNotation = libs.bundles.compose)
                implementation(dependencyNotation = libs.decompose.extensions.compose.jetpack)
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
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "kz.grandera.vlifetesttaskapp"
    multiplatformResourcesClassName = "SharedRes"
    multiplatformResourcesVisibility = MRVisibility.Internal
}