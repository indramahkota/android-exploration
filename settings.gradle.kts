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
            from("com.indramahkota.build.libs.versions:versions-stack:0.1.1")
        }
    }
}

// Set com.indramahkota.build.logic.convention.* plugins to specific version
plugins {
    id("com.indramahkota.build.logic.convention.settings") version "0.4.0"
}

rootProject.name = "android-exploration"
