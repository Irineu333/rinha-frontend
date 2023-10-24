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
                api(compose.material3)
            }
        }

        val desktopMain by getting {
            dependencies {
                api(compose.desktop.common)
                api(compose.preview)
            }
        }
    }
}
