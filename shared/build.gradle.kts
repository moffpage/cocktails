plugins {
    alias(libs.plugins.moko.resources.generator)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose)
    alias(libs.plugins.multiplatform)
}

android {
    namespace = "kz.grandera.vlifetesttaskapp.shared"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        applicationId = "kz.grandera.vlifetesttaskapp"
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

    sourceSets.getByName("main").res.srcDir(File(buildDir, "generated/moko/androidMain/res"))
}

kotlin {
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
            baseName = "shared"
            export(dependency = libs.essenty.lifecycle)
            export(dependency = libs.essenty.backhandler)
            export(dependency = libs.decompose.core)
            export(dependency = project(":common"))
        }
    }

    sourceSets {
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        iosMain {
            dependsOn(commonMain.get())
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        androidMain {
            dependsOn(commonMain.get())
            dependencies {
                implementation(dependencyNotation = libs.koin.android)
                implementation(dependencyNotation = libs.android.activity.compose)
                implementation(dependencyNotation = libs.seismic)
            }
        }

        commonMain.dependencies {
            api(dependencyNotation = project(path = ":common"))
            api(dependencyNotation = libs.decompose.core)

            implementation(dependencyNotation = project(path = ":core"))
            implementation(dependencyNotation = project(path = ":ui_components"))
        }
    }
}