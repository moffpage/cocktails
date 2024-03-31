plugins {
    id("com.android.application")

    kotlin(module = "android")
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

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(dependencyNotation = project(path = ":shared"))

    implementation(dependencyNotation = libs.koin.android)
    implementation(dependencyNotation = libs.android.activity.compose)
    implementation(dependencyNotation = libs.bundles.compose)
    implementation(dependencyNotation = libs.decompose.core)
    implementation(dependencyNotation = libs.mvikotlin.core)
    implementation(dependencyNotation = libs.mvikotlin.main)
}