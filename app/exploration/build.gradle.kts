@file:Suppress("StringLiteralDuplication")

import com.indramahkota.gradle.android.dsl.staging
import com.indramahkota.gradle.common.utils.loadPropertiesFile
import org.gradle.configurationcache.extensions.capitalized

plugins {
  alias(indra.plugins.convention.compose.app)
  alias(libs.plugins.secret.gradle.plugin)
}

val androidApplicationId by extra { "com.indramahkota.android.exploration" }
val androidApplicationVersionCode by extra { 1 }
val androidApplicationVersionName by extra { "0.0.0" }
val androidApplicationName by extra { "Exploration" }
val squareIconName by extra { "@mipmap/ic_launcher_square" }
val roundIconName by extra { "@mipmap/ic_launcher_round" }
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
      manifestPlaceholders["appName"] = androidApplicationName.plus(" ${name.capitalized()}")
      manifestPlaceholders["squareIcon"] = squareIconName.plus("_$name")
      manifestPlaceholders["roundIcon"] = roundIconName.plus("_$name")
    }
    staging {
      manifestPlaceholders["appName"] = androidApplicationName.plus(" ${name.capitalized()}")
      manifestPlaceholders["squareIcon"] = squareIconName.plus("_$name")
      manifestPlaceholders["roundIcon"] = roundIconName.plus("_$name")
    }
    release {
      signingConfig = signingConfigs.getByName("release")
      manifestPlaceholders["appName"] = androidApplicationName
      manifestPlaceholders["squareIcon"] = squareIconName
      manifestPlaceholders["roundIcon"] = roundIconName
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
  implementation(project(":feature:homebase"))

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
