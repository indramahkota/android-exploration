// We want to use new APIs
@file:Suppress("UnstableApiUsage")

// In settings.gradle you can add the repositories you want to add to the project
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "android-exploration"

include(":app:app")
include(":app:regular_feature:homepage")

include(":common:lib_kotlin")
include(":common:lib_android")
include(":common:lib_compose")

include(":data:common")