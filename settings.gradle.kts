rootProject.name = "Vlife_Task"

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

include(":app_android")
include(":common")
include(":ui_components")
include(":core")
include(":shared")
