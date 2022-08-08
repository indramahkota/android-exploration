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
            isAllowInsecureProtocol = true
            setUrl("http://localhost:8081/repository/maven-releases/")
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