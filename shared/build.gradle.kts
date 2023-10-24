group = "com.neo.shared"
version = "1.0.0-DEV"

plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm {
        jvmToolchain(8)
    }

    sourceSets {
        val jvmMain by getting
    }
}
