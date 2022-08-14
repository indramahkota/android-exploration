// We want to use new APIs
@file:Suppress("UnstableApiUsage")

// Applied to the current project with id
plugins {
    id("com.indramahkota.build.logic.convention.android-lib")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

kapt {
    arguments {
        // Make Hilt share the same definition of Components in tests instead of
        // creating a new set of Components per test class.
        arg("dagger.hilt.shareTestComponents", "true")
    }
}

dependencies {
    implementation(project(":common:lib_kotlin"))
    implementation(project(":common:lib_android"))

    // Core KTX
    implementation(libs.core)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    /**
     * Hilt
     * https://dagger.dev/hilt/gradle-setup#using-hilt-with-kotlin
     */
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    androidTestImplementation(libs.dagger.hilt.testing)
    kaptAndroidTest(libs.dagger.hilt.compiler)
    testImplementation(libs.dagger.hilt.testing)
    kaptTest(libs.dagger.hilt.compiler)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room)
    kapt(libs.room.compiler)

    // Okhttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.loggingInterceptor)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // Timber
    implementation(libs.timber)

    // Test
    testImplementation(libs.junit4)

    // Chucker
    implementation(libs.chucker.library)
    releaseImplementation(libs.chucker.library.no.op)
}