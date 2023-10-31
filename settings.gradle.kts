pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "JSONTreeViewer"

include("shared")
include("desktop")
include("web")