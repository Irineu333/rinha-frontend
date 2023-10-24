plugins {
    kotlin("multiplatform") version "1.9.0"
}

group = "com.neo"
version = "1.0.0-DEV"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        jvmToolchain(8)
    }
    
    sourceSets {
        val jvmMain by getting
    }
}
