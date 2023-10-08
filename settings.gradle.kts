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
include(":time_travel_client")
include(":ui_components")
include(":core")
