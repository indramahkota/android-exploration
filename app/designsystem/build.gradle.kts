@file:Suppress("StringLiteralDuplication")

import com.indramahkota.gradle.android.dsl.staging
import com.indramahkota.gradle.common.utils.loadPropertiesFile

plugins {
  alias(indra.plugins.convention.compose.app)
  alias(libs.plugins.secret.gradle.plugin)
}

val androidApplicationId by extra { "com.indramahkota.android.designsystem" }
val androidApplicationName by extra { "Design System" }
val androidApplicationVersionCode by extra { 1 }
val androidApplicationVersionName by extra { "0.0.0" }
val secretPropertiesFile by extra { "../../keystore.properties" }

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

    // https://issuetracker.google.com/issues/295457468 remove line bellow after this issue fixed
    configurations.all {
      resolutionStrategy {
        force("androidx.emoji2:emoji2-views-helper:1.3.0")
        force("androidx.emoji2:emoji2:1.3.0")
      }
    }
  }

  signingConfigs {
    create("release") {
      val propertiesFile = loadPropertiesFile(secretPropertiesFile)
      keyAlias = propertiesFile.getProperty("KEY_ALIAS")
      keyPassword = propertiesFile.getProperty("KEY_PASSWORD")
      storeFile = file(propertiesFile.getProperty("STORE_FILE"))
      storePassword = propertiesFile.getProperty("STORE_PASSWORD")
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
  implementation(project(":feature:designsystem"))

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
}
