plugins {
    id("com.indramahkota.build.logic.convention.detekt")
    id("com.indramahkota.build.logic.convention.android-config")
    id("com.indramahkota.build.logic.convention.compose-config")
}

val androidApplicationId by extra { "com.indramahkota.app.exploration" }
val androidVersionCode by extra { 1 }
val androidVersionName by extra { "0.0.0" }

// Initial configuration for subprojects
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

    // Apply hilt for DI
    android {
        minSdk.set(23)
        targetSdk.set(33)
        usingHilt.set(true)
    }

    // Report directory:
    // - $reportsDir/compose-reports/
    // - $reportsDir/compose-metrics/
    compose {
        // https://developer.android.com/jetpack/androidx/releases/compose-compiler
        compilerVersion.set("1.3.1")
        enableComposeCompilerMetrics.set(true)
        enableComposeCompilerReports.set(true)
    }
}
