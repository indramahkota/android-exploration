@file:Suppress("UnstableApiUsage", "StringLiteralDuplication")

import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.indramahkota.build.logic.convention.android.dsl.staging

plugins {
    id("com.indramahkota.build.logic.convention.compose-app")
    id("com.indramahkota.build.logic.convention.hilt")
}

val androidApplicationName by extra { "Compose Exploration" }
val androidApplicationId by extra { "com.indramahkota.app.exploration" }
val androidApplicationVersionCode by extra { 1 }
val androidApplicationVersionName by extra { "0.0.0" }

// Using initial configuration from root project
android {
    namespace = androidApplicationId
    testNamespace = "$androidApplicationId.test"

    defaultConfig {
        applicationId = androidApplicationId
        versionCode = androidApplicationVersionCode
        versionName = androidApplicationVersionName
    }

    buildTypes {
        debug {
            manifestPlaceholders["appName"] = androidApplicationName
            manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_square_dev"
            manifestPlaceholders["appIconRound"] = "@mipmap/ic_launcher_round_dev"
        }

        staging {
            manifestPlaceholders["appName"] = androidApplicationName
            manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_square_staging"
            manifestPlaceholders["appIconRound"] = "@mipmap/ic_launcher_round_staging"
        }

        release {
            manifestPlaceholders["appName"] = androidApplicationName
            manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_square_release"
            manifestPlaceholders["appIconRound"] = "@mipmap/ic_launcher_round_release"
        }
    }

    applicationVariants.all {
        val variant = this
        variant.outputs.map { it as BaseVariantOutputImpl }.forEach { output ->
            val outputFileName =
                "${androidApplicationName}-V${variant.versionName}-${variant.versionCode}.apk"
            output.outputFileName = outputFileName
        }
    }
}

dependencies {
    implementation(project(":app:navigation"))
    implementation(project(":app:features:splash"))
    implementation(project(":app:features:profile"))
    implementation(project(":app:features:homebase"))
    implementation(project(":app:features:catalogui"))

    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))

    // Metrics
    implementation(libs.androidx.metrics)
    implementation(libs.androidx.tracing.ktx)

    // AndroidX
    implementation(libs.core.ktx)
    implementation(libs.core.splashscreen)
    implementation(libs.activity.compose)
    implementation(libs.window.manager)
    implementation(libs.profileinstaller)

    // UI Compose
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.foundation)
    implementation(libs.compose.foundation.layout)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.util)
    implementation(libs.compose.runtime.livedata)
    implementation(libs.compose.runtime.tracing)

    // Navigation Compose
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    androidTestImplementation(libs.navigation.testing)

    // Others
    implementation(libs.coil)
    implementation(libs.coil.svg)
}
