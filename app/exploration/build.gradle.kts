@file:Suppress("UnstableApiUsage")

plugins {
    id("com.indramahkota.build.logic.convention.compose-app")
    id("com.indramahkota.build.logic.convention.hilt")
}

val androidApplicationId: String by rootProject.extra
val androidVersionCode: Int by rootProject.extra
val androidVersionName: String by rootProject.extra

// Using initial configuration from root project
android {
    namespace = androidApplicationId
    testNamespace = "$androidApplicationId.test"

    defaultConfig {
        applicationId = androidApplicationId
        versionCode = androidVersionCode
        versionName = androidVersionName
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":app:regular_feature:homepage"))
    implementation(project(":app:regular_feature:profile"))

    implementation(libs.activity.compose)
    implementation(libs.core.ktx)
    implementation(libs.core.splashscreen)
    implementation(libs.window.manager)
    implementation(libs.profileinstaller)

    implementation(libs.coil)
    implementation(libs.coil.svg)
}
