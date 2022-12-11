@file:Suppress("UnstableApiUsage", "StringLiteralDuplication")

enableFeaturePreview("VERSION_CATALOGS")

// In settings.gradle you can add the repositories you want to add to the project
pluginManagement {
    repositories {
        maven(url = "https://maven.pkg.github.com/indramahkota/build-logic-public/") {
            name = "GitHubPackages"
            credentials {
                username = providers.gradleProperty("github.username").orNull
                    ?: System.getenv("GITHUB_USERNAME")
                password = providers.gradleProperty("github.token").orNull
                    ?: System.getenv("GITHUB_TOKEN")
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
        maven(url = "https://maven.pkg.github.com/indramahkota/build-logic-public/") {
            name = "GitHubPackages"
            credentials {
                username = providers.gradleProperty("github.username").orNull
                    ?: System.getenv("GITHUB_USERNAME")
                password = providers.gradleProperty("github.token").orNull
                    ?: System.getenv("GITHUB_TOKEN")
            }
        }

        // Register the AndroidX snapshot repository first so snapshots don't attempt (and fail)
        // to download from the non-snapshot repositories
        maven(url = "https://androidx.dev/snapshots/builds/8455591/artifacts/repository") {
            name = "AndroidX Snapshot"
            content {
                // The AndroidX snapshot repository will only have androidx artifacts, don't
                // bother trying to find other ones
                includeGroupByRegex("androidx\\..*")
            }
        }
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from("com.indramahkota.build.libs.versions:versions-stack:0.0.6")
        }
    }
}

// Set com.indramahkota.build.logic.convention.* plugins to specific version
plugins {
    id("com.indramahkota.build.logic.convention.settings") version "0.1.6"
}

rootProject.name = "android-exploration"
