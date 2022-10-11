plugins {
    id("com.indramahkota.build.logic.convention.detekt")
    id("com.indramahkota.build.logic.convention.android-config")
    id("com.indramahkota.build.logic.convention.compose-config")

    // Align version of all subproject modules
    /*id("com.indramahkota.build.logic.convention.android-lib") version "0.0.5" apply false
    id("com.indramahkota.build.logic.convention.android-app") version "0.0.5" apply false
    id("com.indramahkota.build.logic.convention.compose-lib") version "0.0.5" apply false
    id("com.indramahkota.build.logic.convention.compose-app") version "0.0.5" apply false
    id("com.indramahkota.build.logic.convention.hilt") version "0.0.5" apply false*/
}

val androidApplicationId by extra { "com.indramahkota.app.exploration" }
val androidVersionCode by extra { 1 }
val androidVersionName by extra { "0.0.0" }

// Initial configuration for subprojects
// This project using kotlin 1.7.20 by default
indramahkota {
    // Default JavaVersion.VERSION_1_8
    jvmTarget.set(JavaVersion.VERSION_11)

    // Report directory: $reportsDir/detekt-reports/
    detekt {
        // Related with :detektDiff task
        checkOnlyDiffWithBranch("main") {
            fileExtensions = setOf(".kt", ".kts")
        }
    }

    android {
        minSdk.set(23)
        targetSdk.set(33)
    }

    // Report directory:
    // - $reportsDir/compose-reports/
    // - $reportsDir/compose-metrics/
    compose {
        // https://developer.android.com/jetpack/androidx/releases/compose
        // compiler and runtime is mandatory property
        // default is v1.3.2 and v1.3.0-rc01
        compilerVersion.set("1.3.2")
        runtimeVersion.set("1.3.0-rc01")
        enableComposeCompilerMetrics.set(true)
        enableComposeCompilerReports.set(true)
    }
}
