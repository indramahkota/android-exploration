import com.indramahkota.build.logic.convention.publishing.dsl.developer
import com.indramahkota.build.logic.convention.publishing.dsl.mit
import com.indramahkota.build.logic.convention.publishing.dsl.setGitHubProject

plugins {
    id("com.indramahkota.build.logic.convention.detekt")
    id("com.indramahkota.build.logic.convention.android-config")
    id("com.indramahkota.build.logic.convention.compose-config")
    id("com.indramahkota.build.logic.convention.publish-config")
}

allprojects {
    gradle.projectsEvaluated {
        tasks.withType<JavaCompile> {
            options.compilerArgs.addAll(
                listOf("-Xlint:unchecked", "-Xlint:deprecation")
            )
        }
    }
}

// Initial configuration for subprojects
indramahkota {
    // Default JavaVersion.VERSION_1_8
    jvmTarget.set(JavaVersion.VERSION_11)

    // Report directory: $reportsDir/detekt-reports/
    detekt {
        // Related with :detektDiff task
        checkOnlyDiffWithBranch("main") {
            fileExtensions = setOf(".kt", ".kts")
        }
    }

    android {
        minSdk.set(23)
        targetSdk.set(33)
    }

    // Report directory:
    // - $reportsDir/compose-reports/
    // - $reportsDir/compose-metrics/
    compose {
        // https://developer.android.com/jetpack/androidx/releases/compose
        // compiler and runtime is mandatory property
        // default is v1.3.2 and v1.3.0-rc01
        compilerVersion.set("1.4.2")
        runtimeVersion.set("1.3.3")
        enableComposeCompilerMetrics.set(true)
        enableComposeCompilerReports.set(true)
    }

    // Set maven pom for all sub projects
    publishing {
        pom {
            setGitHubProject("indramahkota/android-exploration")

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
