plugins {
    alias(libs.plugins.moko.resources.generator)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.kotlin)
    alias(libs.plugins.multiplatform)
}

android {
    namespace = "kz.grandera.vlifetesttaskapp.android"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        applicationId = "kz.grandera.vlifetesttaskapp.android"
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
            isShrinkResources = false
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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

    sourceSets.getByName("main").res.srcDir(File(buildDir, "generated/moko/androidMain/res"))
}

kotlin {
    applyDefaultHierarchyTemplate()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
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
            export(dependency = libs.moko.resources.common)
            export(dependency = libs.essenty.lifecycle)
            export(dependency = libs.decompose.core)
            export(dependency = project(":common"))
            export(dependency = project(":ui_components"))
        }
    }

    sourceSets {
        androidMain {
            dependsOn(commonMain.get())
            dependencies {
                implementation(dependencyNotation = libs.koin.android)
                implementation(dependencyNotation = libs.seismic)
                implementation(dependencyNotation = libs.android.activity.compose)
            }
        }

        commonMain.dependencies {
            api(dependencyNotation = project(path = ":core"))
            api(dependencyNotation = project(path = ":common"))
            api(dependencyNotation = project(path = ":ui_components"))

            api(dependencyNotation = libs.decompose.core)
            implementation(dependencyNotation = libs.napier)
        }
    }
}