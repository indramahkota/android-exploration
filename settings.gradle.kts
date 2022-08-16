// We want to use new APIs
@file:Suppress("UnstableApiUsage")

// In settings.gradle you can add the repositories you want to add to the project
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://maven.pkg.github.com/indramahkota/build-logic-convention/")
            credentials {
                username = providers.gradleProperty("github.username").orNull
                    ?: System.getenv("GITHUB_USERNAME")
                password = providers.gradleProperty("github.token").orNull
                    ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

rootProject.name = "android-exploration"

include(":app:exploration")
include(":app:regular_feature:homepage")
include(":app:regular_feature:profile")