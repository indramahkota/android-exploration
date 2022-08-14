// We want to use new APIs
@file:Suppress("UnstableApiUsage")

// Applied to the current project with id
plugins {
    id("com.indramahkota.build.logic.convention.android-lib")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

dependencies {
    // Core KTX
    implementation(libs.core)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    // Timber
    implementation(libs.timber)

    // Test
    testImplementation(libs.junit4)
}