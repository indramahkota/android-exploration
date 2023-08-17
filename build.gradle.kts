import com.indramahkota.gradle.publishing.dsl.developer
import com.indramahkota.gradle.publishing.dsl.mit
import com.indramahkota.gradle.publishing.dsl.setGitHubProject

plugins {
  alias(indra.plugins.convention.android.app) apply false
  alias(indra.plugins.convention.android.lib) apply false
  alias(indra.plugins.convention.compose.app) apply false
  alias(indra.plugins.convention.compose.lib) apply false
  alias(indra.plugins.convention.publishing) apply false

  alias(indra.plugins.convention.android.config)
  alias(indra.plugins.convention.compose.config)
  alias(indra.plugins.convention.publish.config)
  alias(indra.plugins.convention.detekt)
}

// Initial configuration for subprojects
indramahkota {
  jvmTarget.set(JavaVersion.VERSION_11)

  // Report directory:
  // rootDir/reports/detekt-reports/
  detekt {
    checkOnlyDiffWithBranch("main") {
      fileExtensions = setOf(".kt", ".kts")
    }
  }

  android {
    minSdk.set(23)
    targetSdk.set(34)
  }

  // Report directory:
  // rootDir/reports/compose-reports/
  // rootDir/reports/compose-metrics/
  compose {
    compilerVersion.set("1.5.1")
    enableComposeCompilerMetrics.set(true)
    enableComposeCompilerReports.set(true)
  }

  // Set maven pom for all sub projects
  publishing {
    pom {
      setGitHubProject {
        owner = "indramahkota"
        repository = "android-exploration"
      }

      licenses {
        mit()
      }

      developers {
        developer(
          id = "indramahkota",
          name = "Indra Mahkota",
          email = "indramahkota1@gmail.com",
        )
      }
    }
  }
}
