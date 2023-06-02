plugins {
    kotlin("multiplatform") version "1.8.21"
    id("jacoco")
}

group = "com.carterharrison"
version = "v0.1.0-beta.0"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    sourceSets {
        val commonMain by getting

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

//jacocoTestReport {
//    reports {
//        xml.required = true
//        html.required = false
//    }
//}