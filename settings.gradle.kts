// We want to use new APIs
@file:Suppress("UnstableApiUsage")

// In settings.gradle you can add the repositories you want to add to the project
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { setUrl("https://jitpack.io") }
    }
}

rootProject.name = "mad-base-sample"

include(":app:app")
include(":app:regular_feature:homepage")

include(":common:lib_kotlin")
include(":common:lib_android")
include(":common:lib_compose")

include(":data:common")