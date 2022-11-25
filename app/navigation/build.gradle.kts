plugins {
    id("com.indramahkota.build.logic.convention.compose-lib")
    id("com.indramahkota.build.logic.convention.hilt")
}

android {
    namespace = "com.indramahkota.app.exploration.navigation"
    testNamespace = "com.indramahkota.app.exploration.navigation.test"
}

dependencies {
    // Navigation Compose
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    androidTestImplementation(libs.navigation.testing)
}
