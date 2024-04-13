import dev.icerock.gradle.MRVisibility

plugins {
    alias(libs.plugins.moko.resources.generator)
    id(libs.plugins.android.library.get().pluginId)
    alias(libs.plugins.multiplatform)
}

android {
    namespace = "kz.grandera.vlifetesttaskapp.ui_components"
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
        commonMain {
            dependencies {
                implementation(dependencyNotation = libs.moko.resources.common)
            }
        }

        androidMain {
            dependsOn(commonMain.get())
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