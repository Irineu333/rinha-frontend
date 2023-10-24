group = "com.neo"

plugins {
    kotlin("multiplatform") version "1.9.0" apply false
    id("org.jetbrains.compose") version "1.5.3" apply false
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}