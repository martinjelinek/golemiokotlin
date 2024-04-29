import com.vanniktech.maven.publish.SonatypeHost

mavenPublishing {
    coordinates("io.github.martinjelinek", "golemiokotlin", "0.1.0")

    pom {
        name = "Golemio Kotlin"
        description = "KMM library for fetching and deserializing data from api.golemio.cz"
        inceptionYear = "2024"
        url = "https://github.com/martinjelinek/golemiokotlin"
        licenses {
            license {
                name = "The MIT License"
                url = "https://opensource.org/licenses/MIT"
                distribution = "https://opensource.org/licenses/MIT"
            }
        }
        developers {
            developer {
                id = "martinjelinek"
                name = "Martin Jelinek"
                url = "https://github.com/martinjelinek/"
            }
        }
        scm {
            url = "https://github.com/martinjelinek/golemiokotlin/"
            connection = "scm:git:git://github.com/martinjelinek/golemiokotlin.git"
            developerConnection = "scm:git:ssh://git@github.com/martinjelinek/golemiokotlin.git"
        }
    }
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
}

plugins {
    id ("com.vanniktech.maven.publish") version "0.28.0"
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
}

group = "io.github.martinjelinek"
version = "0.1.0"

kotlin {
    applyDefaultHierarchyTemplate()
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
        androidTarget {
            publishLibraryVariants("release", "debug")
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
        }

        commonTest.dependencies {
            implementation(libs.common.serialization.json)
            implementation(kotlin("test"))
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "io.github.martinjelinek.golemiokotlin"
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

tasks.register("testClasses") {
    // task configuration
}
