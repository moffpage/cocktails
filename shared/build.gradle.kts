plugins {
    kotlin(module = "multiplatform")

    id("com.android.library")
}

android {
    namespace = "kz.grandera.vlifetesttaskapp.shared"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            export(dependency = libs.moko.resources.common)
            export(dependency = libs.decompose.core)
            export(dependency = project(":core"))
            export(dependency = project(":common"))
            export(dependency = project(":ui_components"))
        }
    }

    androidTarget()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(dependencyNotation = project(path = ":core"))
                api(dependencyNotation = project(path = ":common"))
                api(dependencyNotation = project(path = ":ui_components"))

                api(dependencyNotation = libs.napier)
                api(dependencyNotation = libs.reaktive)
                api(dependencyNotation = libs.decompose.core)
            }
        }
    }
}