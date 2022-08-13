// We want to use new APIs
@file:Suppress("UnstableApiUsage")

// Applied to the current project with id
plugins {
    id("com.indramahkota.android-library")
    id("com.indramahkota.detekt")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    /**
     * https://developer.android.com/jetpack/androidx/releases/compose-runtime#declaring_dependencies
     * */
    buildFeatures {
        compose = true
    }

    /**
     * Same as @buildFeatures reference
     * */
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-rc02"
    }
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

    // Common Libs
    implementation(libs.appcompat)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.constraintlayout)
    implementation(libs.constraintlayout.compose)

    // Compose
    implementation(libs.activity.compose)
    implementation(libs.compose.runtime)
    implementation(libs.compose.foundation)
    implementation(libs.compose.foundation.layout)
    implementation(libs.compose.ui)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material3)
    debugImplementation(libs.compose.ui.tooling)
    androidTestImplementation(libs.compose.ui.test.junit4)

    // Coil Compose
    implementation(libs.coil.compose)

    // Navigation Component
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.dynamicFeatures.fragment)
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    androidTestImplementation(libs.navigation.testing)

    // Lifecycle ktx
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel.compose)

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

    // Timber
    implementation(libs.timber)

    // Test
    testImplementation(libs.junit4)
}