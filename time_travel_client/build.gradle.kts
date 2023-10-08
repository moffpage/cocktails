import dev.icerock.gradle.MRVisibility

plugins {
    kotlin(module = "multiplatform")

    id("com.android.library")
    id("dev.icerock.mobile.multiplatform-resources")
}

android {
    namespace = "kz.grandera.vlifetesttaskapp.time_travel_client"
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
            baseName = "time_travel_client"
            transitiveExport = true
            export(dependency = libs.moko.resources.common)
            export(dependency = libs.decompose.core)
            export(dependency = libs.mvikotlin.timetravel)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(dependencyNotation = libs.moko.resources.common)
                api(dependencyNotation = libs.mvikotlin.timetravel)

                implementation(dependencyNotation = project(path = ":core"))

                implementation(dependencyNotation = libs.ktor.utils)
                implementation(dependencyNotation = libs.koin3.core)
                implementation(dependencyNotation = libs.decompose.core)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(dependencyNotation = project(path = ":ui_components"))

                implementation(dependencyNotation = libs.moko.resources.compose)
                implementation(dependencyNotation = libs.bundles.compose)
                implementation(dependencyNotation = libs.android.activity.compose)
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
    multiplatformResourcesPackage = "kz.grandera.vlifetesttaskapp.time_travel_client"
    multiplatformResourcesClassName = "SharedRes"
    multiplatformResourcesVisibility = MRVisibility.Internal
}