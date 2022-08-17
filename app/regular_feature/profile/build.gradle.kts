// We want to use new APIs
@file:Suppress("UnstableApiUsage")

plugins {
    id("com.indramahkota.build.logic.convention.android-lib")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

// Using configuration from root project
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
    // Kotlin
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    // Android
    implementation(libs.core)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.dynamicFeatures.fragment)
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)

    // Android Compose
    implementation(libs.activity.compose)
    implementation(libs.constraintlayout.compose)
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    androidTestImplementation(libs.navigation.testing)
    implementation(libs.lifecycle.viewmodel.compose)

    // Compose
    implementation(libs.compose.runtime)
    implementation(libs.compose.foundation)
    implementation(libs.compose.foundation.layout)
    implementation(libs.compose.ui)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material3)
    debugImplementation(libs.compose.ui.tooling)
    androidTestImplementation(libs.compose.ui.test.junit4)

    // Compose Accompanist
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.systemuicontroller)

    // Compose Coil
    implementation(libs.coil.compose)

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
}

repositories {
    google()
    mavenCentral()
}
