@file:Suppress("StringLiteralDuplication")

import com.google.android.libraries.mapsplatform.secrets_gradle_plugin.loadPropertiesFile
import com.indramahkota.gradle.android.dsl.staging

plugins {
  alias(indra.plugins.convention.compose.app)
  alias(libs.plugins.secret.gradle.plugin)
}

val androidApplicationName by extra { "Exploration" }
val androidApplicationId by extra { "com.indramahkota.android.exploration" }
val androidApplicationVersionCode by extra { 1 }
val androidApplicationVersionName by extra { "0.0.0" }
val secretPropertiesFile by extra { "../../secrets.properties" }

// Using initial configuration from root project
android {
  namespace = androidApplicationId

  buildFeatures {
    buildConfig = true
  }

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
      signingConfig = signingConfigs.getByName("release")
      manifestPlaceholders["appName"] = androidApplicationName
      manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_square_release"
      manifestPlaceholders["appIconRound"] = "@mipmap/ic_launcher_round_release"
    }
  }

  // https://issuetracker.google.com/issues/295457468 remove line bellow after this issue fixed
  defaultConfig {
    configurations.all {
      resolutionStrategy {
        force("androidx.emoji2:emoji2-views-helper:1.3.0")
        force("androidx.emoji2:emoji2:1.3.0")
      }
    }
  }
}

// Mandatory for get data from secrets.properties in this module
secrets {
  propertiesFileName = "secrets.properties"
  defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
  // Core
  implementation(project(":core:designsystem"))
  implementation(project(":core:media"))
  implementation(project(":core:navigation"))
  implementation(project(":core:ui"))

  // Features
  implementation(project(":features:designsystem"))
  implementation(project(":features:homebase"))

  // AndroidX
  implementation(libs.androidx.metrics)
  implementation(libs.androidx.tracing.ktx)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.core.splashscreen)
  implementation(libs.androidx.window)
  implementation(libs.androidx.profileinstaller)

  // Compose
  implementation(libs.activity.compose)
  implementation(libs.navigation.compose)
  implementation(libs.bundles.androidx.compose.bundle)
  androidTestImplementation(libs.navigation.compose)

  // Others
  implementation(libs.coil)
  implementation(libs.coil.svg)

  // Koin
  implementation(libs.koin.core)
  implementation(libs.koin.core.coroutines)
  implementation(libs.koin.android)
}
