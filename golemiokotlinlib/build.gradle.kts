plugins {
    id("maven-publish")
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
}

kotlin {
    applyDefaultHierarchyTemplate()
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "golemiokotlin"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentnegotiation)
            implementation(libs.ktor.client.json)
            implementation(libs.common.serialization.json)
            implementation(libs.ktor.client.cio)
        }

        commonTest.dependencies {
            implementation(libs.common.serialization.json)
            implementation(kotlin("test"))
        }
    }
}

android {
    namespace = "cz.vse.golemiokotlin"
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

group = "cz.vse"
version = "1.4"

publishing {
    repositories {
        mavenLocal()
    }
    publications.withType<MavenPublication> {
        artifactId ="golemiokotlin"
    }
}

tasks.register("testClasses") {
    // task configuration
}
