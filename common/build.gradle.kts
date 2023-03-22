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

    sourceSets.getByName("main").res.srcDir(File(buildDir, "generated/moko/androidMain/res"))
}

kotlin {
    explicitApiWarning()

    android {
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
            export(dependency = libs.moko.resources.common)
            export(dependency = libs.decompose.core)
            export(dependency = libs.mvikotlin.timetravel)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(dependencyNotation = libs.napier)
                api(dependencyNotation = libs.reaktive)
                api(dependencyNotation = libs.moko.resources.common)
                api(dependencyNotation = libs.decompose.core)
                api(dependencyNotation = libs.mvikotlin.timetravel)

                implementation(dependencyNotation = libs.klock)
                implementation(dependencyNotation = libs.ktor.core)
                implementation(dependencyNotation = libs.ktor.logging)
                implementation(dependencyNotation = libs.ktor.serialization.json)
                implementation(dependencyNotation = libs.ktor.contentnegotiation)
                implementation(dependencyNotation = libs.koin3.core)
                implementation(dependencyNotation = libs.mvikotlin.rx)
                implementation(dependencyNotation = libs.mvikotlin.logging)

                implementation(dependencyNotation = libs.bundles.mvikotlin.common)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(dependencyNotation = libs.coil.compose)
                implementation(dependencyNotation = libs.ktor.engine.okhttp)
                implementation(dependencyNotation = libs.moko.resources.compose)
                implementation(dependencyNotation = libs.lottie.compose)
                implementation(dependencyNotation = libs.android.core)
                implementation(dependencyNotation = libs.android.activity.compose)
                implementation(dependencyNotation = libs.decompose.extensions.compose.jetpack)

                implementation(dependencyNotation = libs.bundles.compose)
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

multiplatformResources {
    multiplatformResourcesPackage = "kz.grandera.vlifetesttaskapp"
    multiplatformResourcesClassName = "SharedRes"
    multiplatformResourcesVisibility = MRVisibility.Internal
}