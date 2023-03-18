buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(dependencyNotation = libs.gradle.kotlin)
        classpath(dependencyNotation = libs.gradle.android)
        classpath(dependencyNotation = libs.kotlin.serialization)
        classpath(dependencyNotation = libs.moko.resources.generator)
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
