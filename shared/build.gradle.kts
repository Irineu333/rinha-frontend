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

                // Kotlin Serialization
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
            }
        }

        val desktopMain by getting {
            dependencies {

                // Compose
                api(compose.desktop.common)
                api(compose.preview)

                // For Dispatchers.Main
                api("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.6.4")
            }
        }
    }
}
