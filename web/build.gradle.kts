group = "com.neo.web"
version = "1.0.0-DEV"

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {

    jvmToolchain(11)

    js(IR) {

        binaries.executable()

        browser()
    }

    sourceSets {

        val jsMain by getting {
            dependencies {
                implementation(project(":shared"))
            }
        }
    }
}

compose.experimental {
    web.application {}
}