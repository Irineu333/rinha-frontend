group = "com.neo.shared"
version = "1.0.0-DEV"

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvmToolchain(11)

    jvm("desktop")

    js {
        browser()
    }

    sourceSets {

        val commonMain by getting {
            dependencies {

                // Compose
                api(compose.material3)

                // Voyager Navigator
                api("cafe.adriel.voyager:voyager-navigator:1.0.0-rc05")
            }
        }

        val desktopMain by getting {
            dependencies {

                // Compose
                api(compose.desktop.common)
                api(compose.preview)

                // File Picker
                api("com.darkrockstudios:mpfilepicker:2.1.0")
            }
        }
    }
}
