enableFeaturePreview("VERSION_CATALOGS")

// In settings.gradle you can add the repositories you want to add to the project
pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/indramahkota/build-logic-public/")
            credentials {
                username = providers.gradleProperty("github.username").orNull
                    ?: System.getenv("GITHUB_USERNAME") ?: "indramahkota"
                // Artifact available on public repository
                password = providers.gradleProperty("github.token").orNull
                    ?: System.getenv("GITHUB_TOKEN") ?: ""
            }
        }
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // Register the AndroidX snapshot repository first so snapshots don't attempt (and fail)
        // to download from the non-snapshot repositories
        maven(url = "https://androidx.dev/snapshots/builds/8455591/artifacts/repository") {
            content {
                // The AndroidX snapshot repository will only have androidx artifacts, don't
                // bother trying to find other ones
                includeGroupByRegex("androidx\\..*")
            }
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "android-exploration"

include(":app:exploration")
include(":app:regular_feature:homepage")
include(":app:regular_feature:profile")
