@file:Suppress("UnstableApiUsage", "StringLiteralDuplication")

import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.indramahkota.build.logic.convention.android.dsl.qa

plugins {
    id("com.indramahkota.build.logic.convention.compose-app")
    id("com.indramahkota.build.logic.convention.hilt")
}

val androidApplicationName by extra { "Exploration" }
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
            resValue("string", "app_name", "[DBG] $androidApplicationName")
            manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_square_dev"
            manifestPlaceholders["appIconRound"] = "@mipmap/ic_launcher_round_dev"
        }

        qa {
            resValue("string", "app_name", "[STG] $androidApplicationName")
            manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_square_staging"
            manifestPlaceholders["appIconRound"] = "@mipmap/ic_launcher_round_staging"
        }

        release {
            resValue("string", "app_name", androidApplicationName)
            manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_square_release"
            manifestPlaceholders["appIconRound"] = "@mipmap/ic_launcher_round_release"
        }
    }

    applicationVariants.all {
        val variant = this
        variant.outputs.map { it as BaseVariantOutputImpl }.forEach { output ->
            val outputFileName =
                "$androidApplicationName-V${variant.versionName}-${variant.versionCode}.apk"
            output.outputFileName = outputFileName
        }
    }
}

dependencies {
    implementation(project(":app:regular_feature:homepage"))
    implementation(project(":app:regular_feature:profile"))

    implementation(libs.androidx.metrics)
    implementation(libs.androidx.tracing.ktx)

    implementation(libs.core.ktx)
    implementation(libs.core.splashscreen)
    implementation(libs.activity.compose)
    implementation(libs.window.manager)
    implementation(libs.profileinstaller)

    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.foundation.layout)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.ui.util)
    implementation(libs.compose.runtime.livedata)
    implementation(libs.compose.runtime.tracing)

    implementation(libs.coil)
    implementation(libs.coil.svg)

    implementation(libs.timber)
}
