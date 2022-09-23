plugins {
    id("com.indramahkota.build.logic.convention.detekt")

    id("com.indramahkota.build.logic.convention.android-config")
    id("com.indramahkota.build.logic.convention.android-lib") apply false
    id("com.indramahkota.build.logic.convention.android-app") apply false

    id("com.indramahkota.build.logic.convention.compose-config")
    id("com.indramahkota.build.logic.convention.compose-lib") apply false
    id("com.indramahkota.build.logic.convention.compose-app") apply false
}

val androidApplicationId by extra { "com.indramahkota.app.exploration" }
val androidVersionCode by extra { 1 }
val androidVersionName by extra { "0.0.0" }

// Initial configuration for subprojects
indramahkota {
    // Default $root/config/
    configsDir.set(file("config/"))

    // Default $root/build/reports/
    reportsDir.set(file("build/reports/"))

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
        targetSdk.set(32)
    }

    // Report directory:
    // - $reportsDir/compose-reports/
    // - $reportsDir/compose-metrics/
    compose {
        compilerVersion.set("1.3.0")
        enableComposeCompilerMetrics.set(true)
        enableComposeCompilerReports.set(true)
    }
}
