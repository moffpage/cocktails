plugins {
    id("com.android.application")
    kotlin(module = "android")
}

android {
    namespace = "kz.grandera.vlifetesttaskapp.android"

    compileSdk = 33

    defaultConfig {
        applicationId = "kz.grandera.vlifetesttaskapp.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    implementation(dependencyNotation = project(path = ":common"))

    implementation(dependencyNotation = libs.bundles.compose)

    implementation(dependencyNotation = libs.koin3.android)
    implementation(dependencyNotation = libs.android.activity.compose)
    implementation(dependencyNotation = libs.decompose.core)
    implementation(dependencyNotation = libs.accompanist.systemuicontroller)
}