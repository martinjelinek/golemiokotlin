@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("maven-publish")
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
    alias(libs.plugins.detekt)
}

kotlin {
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
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.contentnegotiation)
                implementation(libs.ktor.client.json)
                implementation(libs.common.serialization.json)
                implementation(libs.ktor.client.cio)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.common.serialization.json)
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "cz.vse.golemiokotlin"
            artifactId = "library"
            version = "1.1"

            afterEvaluate { artifact(tasks.getByName("bundleReleaseAar")) }
        }
    }

    tasks.register("testClasses") {
        // task configuration
    }
}