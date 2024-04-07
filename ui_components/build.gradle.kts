import dev.icerock.gradle.MRVisibility

plugins {
    kotlin(module = "multiplatform")

    id("com.android.library")

    id("dev.icerock.mobile.multiplatform-resources")
}

android {
    namespace = "kz.grandera.vlifetesttaskapp.ui_components"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        consumerProguardFiles("consumer-rules.pro")
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
        val commonMain by getting {
            dependencies {
                implementation(dependencyNotation = libs.moko.resources.common)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                api(dependencyNotation = libs.moko.resources.compose)
                api(dependencyNotation = libs.coil.compose)
                api(dependencyNotation = libs.lottie.compose)
                api(dependencyNotation = libs.bundles.compose)
                api(dependencyNotation = libs.compose.tooling)
            }
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "kz.grandera.vlifetesttaskapp.ui_components"
    multiplatformResourcesClassName = "UiComponentsRes"
    multiplatformResourcesVisibility = MRVisibility.Internal
}