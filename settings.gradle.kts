// We want to use new APIs
@file:Suppress("UnstableApiUsage")

// In settings.gradle you can add the repositories you want to add to the project
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { setUrl("https://jitpack.io") }
        maven {
            url = uri("https://maven.pkg.github.com/indramahkota/build-logic-convention/")
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

rootProject.name = "android-exploration"

include(":app:app")
include(":app:regular_feature:homepage")

include(":common:lib_kotlin")
include(":common:lib_android")
include(":common:lib_compose")

include(":data:common")