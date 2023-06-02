import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    kotlin("multiplatform") version "1.8.21"
}

group = "com.carterharrison"
version = "v0.1.0-beta.0"


@OptIn(ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    jvm {
        jvmToolchain(11)
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    iosArm64()
    iosSimulatorArm64()
    macosArm64()
    js(IR)

    sourceSets {
        val commonMain by getting{
            dependencies{
                implementation("com.ionspin.kotlin:bignum:0.3.8")
                implementation("org.kotlincrypto:secure-random:0.1.0")
                implementation("org.kotlincrypto.hash:sha2:0.2.4")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}