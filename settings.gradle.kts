pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Vlife_Task"
include(":app_android")
include(":common")
include(":ui_components")
include(":core")
include(":shared")
