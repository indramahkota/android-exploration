@file:Suppress("UnstableApiUsage", "StringLiteralDuplication", "DSL_SCOPE_VIOLATION")

import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.google.android.libraries.mapsplatform.secrets_gradle_plugin.loadPropertiesFile
import com.indramahkota.gradle.android.dsl.staging

plugins {
    id("com.indramahkota.compose.app")
    id("com.indramahkota.hilt")

    // Initiate others plugins
    alias(libs.plugins.secret.gradle.plugin)
}

val androidApplicationName by extra { "Compose Exploration" }
val androidApplicationId by extra { "com.indramahkota.app.exploration" }
val androidApplicationVersionCode by extra { 1 }
val androidApplicationVersionName by extra { "0.0.0" }
val secretPropertiesFile by extra { "../../secrets.properties" }

// Using initial configuration from root project
android {
    namespace = androidApplicationId
    testNamespace = "$androidApplicationId.test"

    defaultConfig {
        applicationId = androidApplicationId
        versionCode = androidApplicationVersionCode
        versionName = androidApplicationVersionName
    }

    signingConfigs {
        create("release") {
            val secrets = loadPropertiesFile(secretPropertiesFile)
            keyAlias = secrets.getProperty("KEY_ALIAS")
            keyPassword = secrets.getProperty("KEY_PASSWORD")
            storeFile = file(secrets.getProperty("STORE_FILE"))
            storePassword = secrets.getProperty("STORE_PASSWORD")
        }
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
            signingConfig = signingConfigs.getByName("release")
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

// Mandatory for get data from secrets.properties in this module
secrets {
    // Change the properties file from the default "local.properties" in your root project
    // to another properties file in your root project.
    propertiesFileName = "secrets.properties"

    // A properties file containing default secret values. This file can be checked in version
    // control.
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    implementation(project(":features:catalogui"))
    implementation(project(":features:homebase"))
    implementation(project(":features:profile"))
    implementation(project(":features:splash"))

    implementation(project(":core:designsystem"))
    implementation(project(":core:media"))
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))

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
