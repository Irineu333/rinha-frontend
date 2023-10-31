plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "com.neo.desktop"
version = "1.0.0-DEV"

dependencies {
    implementation(project(":shared"))
    implementation(compose.desktop.currentOs)
}

kotlin {
    jvmToolchain(11)
}

compose.desktop {
    application {
        mainClass = "MainKt"
    }
}
