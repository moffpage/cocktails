import dev.icerock.gradle.MRVisibility

plugins {
    alias(libs.plugins.moko.resources.generator)
    id(libs.plugins.android.library.get().pluginId)
    alias(libs.plugins.compose)
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

multiplatformResources {
    resourcesPackage.set("kz.grandera.vlifetesttaskapp.common")
    resourcesClassName.set("CommonRes")
    resourcesVisibility.set(MRVisibility.Internal)
}