import com.indramahkota.gradle.publishing.dsl.developer
import com.indramahkota.gradle.publishing.dsl.mit
import com.indramahkota.gradle.publishing.dsl.setGitHubProject

plugins {
    alias(indra.plugins.build.logic.android.app) apply false
    alias(indra.plugins.build.logic.android.lib) apply false
    alias(indra.plugins.build.logic.compose.app) apply false
    alias(indra.plugins.build.logic.compose.lib) apply false
    alias(indra.plugins.build.logic.publishing) apply false

    alias(indra.plugins.build.logic.android.config)
    alias(indra.plugins.build.logic.compose.config)
    alias(indra.plugins.build.logic.publish.config)
    alias(indra.plugins.build.logic.detekt)
}

// Initial configuration for subprojects
indramahkota {
    // Default JavaVersion.VERSION_1_8
    jvmTarget.set(JavaVersion.VERSION_11)
    withCompilerArgs {
        compilerArgs = setOf(
            "-Xlint:unchecked", "-Xlint:deprecation"
        )
    }

    // Report directory:
    // rootDir/reports/detekt-reports/
    detekt {
        // Related with :detektDiff task
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
                    email = "indramahkota1@gmail.com"
                )
            }
        }
    }
}
