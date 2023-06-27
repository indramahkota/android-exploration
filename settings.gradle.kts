@file:Suppress("UnstableApiUsage", "StringLiteralDuplication")

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

        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from("com.indramahkota.gradle.version:android-stack:0.0.1")
        }
    }
}

rootProject.name = "android-exploration"

// Set com.indramahkota.* plugins to specific version
plugins {
    id("com.indramahkota.settings") version "0.0.3"
}
