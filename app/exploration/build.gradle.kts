plugins {
    id("com.indramahkota.build.logic.convention.android-app")
}

// Using configuration from root project
android {
    defaultConfig {
        applicationId = "com.indramahkota.app.exploration"
        versionCode = 1
        versionName = "1.0.0"
    }
}

dependencies {
    implementation(libs.core)
    implementation(libs.appcompat)

    implementation(libs.material)
    implementation(libs.compose.material3)
}
