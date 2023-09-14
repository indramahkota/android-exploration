import com.indramahkota.gradle.publishing.dsl.developer
import com.indramahkota.gradle.publishing.dsl.mit
import com.indramahkota.gradle.publishing.dsl.setGitHubProject

plugins {
  alias(libs.plugins.kotlin.ksp) apply false
  alias(libs.plugins.hilt.android) apply false

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

indramahkota {
  jvmTarget.set(JavaVersion.VERSION_11)

  android {
    minSdk.set(23)
    targetSdk.set(34)
  }

  compose {
    compilerVersion.set("1.5.3")
    enableComposeCompilerMetrics.set(true)
    enableComposeCompilerReports.set(true)
  }

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

tasks.register("clean", Delete::class.java) {
  description = "Remove all the build files and intermediate build outputs"
  subprojects.forEach {
    if (it.project.plugins.hasPlugin("com.android.library") ||
      it.project.plugins.hasPlugin("com.android.application")
    ) {
      // Execute subprojects clean task before delete root build directory
      dependsOn(it.tasks.named("clean"))
    }
  }
  delete(
    allprojects.map {
      it.layout.buildDirectory.asFile
    },
  )
}
