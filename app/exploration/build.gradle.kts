plugins {
    id("com.indramahkota.build.logic.convention.android-app")
    id("com.indramahkota.build.logic.convention.compose-app")
}

val androidApplicationId by extra { "com.indramahkota.app.exploration" }
val androidVersionCode by extra { 1 }
val androidVersionName by extra { "0.0.0" }

// Using initial configuration from root project
android {
    namespace = androidApplicationId

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
