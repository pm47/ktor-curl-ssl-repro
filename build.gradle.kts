import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.multiplatform)
}

val currentOs = org.gradle.internal.os.OperatingSystem.current()
val arch = System.getProperty("os.arch")

kotlin {

    fun KotlinNativeTarget.configureBinaries() {
        binaries {
            executable {
                entryPoint = "org.example.ktor.main"
                optimized = false // without this, release mode throws 'Index 0 out of bounds for length 0' in StaticInitializersOptimization.kt
            }
        }
    }

    if (currentOs.isWindows) {
        mingwX64 {
            configureBinaries()
        }
    }

    if (currentOs.isLinux && arch != "aarch64") {
        linuxX64 {
            configureBinaries()
        }
        linuxArm64 {
            configureBinaries()
        }
    }

    if (currentOs.isMacOsX) {
        macosX64 {
            configureBinaries()
        }
        macosArm64 {
            configureBinaries()
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("io.ktor:ktor-serialization-kotlinx-json:${libs.versions.ktor.get()}")
                implementation("io.ktor:ktor-client-core:${libs.versions.ktor.get()}")
                implementation("io.ktor:ktor-client-content-negotiation:${libs.versions.ktor.get()}")
                implementation("io.ktor:ktor-client-auth:${libs.versions.ktor.get()}")
                implementation("io.ktor:ktor-client-json:${libs.versions.ktor.get()}")
                implementation("io.ktor:ktor-client-curl:${libs.versions.ktor.get()}")
            }
        }
        nativeMain {
            dependencies {
                implementation("io.ktor:ktor-client-curl:${libs.versions.ktor.get()}")
            }
        }
    }
}
