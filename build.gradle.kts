buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(dependencyNotation = libs.moko.resources.generator)
        classpath(dependencyNotation = libs.gradle.kotlin)
        classpath(dependencyNotation = libs.gradle.android)
        classpath(dependencyNotation = libs.gradle.kotlinx.serialization)
        classpath(libs.gradle.kotlin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
