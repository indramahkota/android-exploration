import com.indramahkota.gradle.common.utils.loadPropertiesFile

plugins {
  alias(indra.plugins.convention.compose.app)
  alias(libs.plugins.kotlin.ksp)
  alias(libs.plugins.hilt.android)
  alias(libs.plugins.secret.gradle.plugin)
  alias(exploration.plugins.gms.google.services)
}

android {
  namespace = "com.indramahkota.android.exploration"

  buildFeatures {
    buildConfig = true
  }

  defaultConfig {
    applicationId = "com.indramahkota.android.exploration"
    versionCode = 1
    versionName = "0.0.0"
  }

  signingConfigs {
    create("release") {
      val properties = loadPropertiesFile("../../keystore.properties")
      keyAlias = properties.getProperty("KEY_ALIAS")
      keyPassword = properties.getProperty("KEY_PASSWORD")
      storeFile = file(properties.getProperty("STORE_FILE"))
      storePassword = properties.getProperty("STORE_PASSWORD")
    }
  }

  androidComponents {
    finalizeDsl {
      buildTypes {
        debug {
          manifestPlaceholders.apply {
            put("appName", "Exploration - Debug")
            put("roundIcon", "@mipmap/ic_launcher_round_debug")
            put("squareIcon", "@mipmap/ic_launcher_square_debug")
          }
        }
        release {
          signingConfig = signingConfigs.getByName("release")
          manifestPlaceholders.apply {
            put("appName", "Exploration")
            put("roundIcon", "@mipmap/ic_launcher_round")
            put("squareIcon", "@mipmap/ic_launcher_square")
          }
        }
      }
    }
  }
}

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
  implementation(project(":feature:auth"))
  implementation(project(":feature:homebase"))
  implementation(project(":feature:profile"))

  // Firebase
  implementation(exploration.firebase.bom)
  implementation(exploration.firebase.auth.ktx)
  implementation(exploration.play.services.auth)

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

  // Hilt
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
  androidTestImplementation(libs.hilt.android.testing)
  kspAndroidTest(libs.hilt.compiler)
  testImplementation(libs.hilt.android.testing)
  kspTest(libs.hilt.compiler)
}
