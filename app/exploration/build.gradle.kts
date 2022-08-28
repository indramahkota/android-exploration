plugins {
    id("com.indramahkota.build.logic.convention.android-app")
    id("com.indramahkota.build.logic.convention.compose-app")
}

val androidApplicationId: String by rootProject.extra
val androidVersionCode: Int by rootProject.extra
val androidVersionName: String by rootProject.extra

// Using configuration from root project
android {
    defaultConfig {
        applicationId = androidApplicationId
        versionCode = androidVersionCode
        versionName = androidVersionName
    }
}

dependencies {
    implementation(libs.core)
    implementation(libs.appcompat)

    implementation(libs.material)
    implementation(libs.compose.material3)
}
