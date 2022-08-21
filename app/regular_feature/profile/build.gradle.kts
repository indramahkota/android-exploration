// We want to use new APIs
@file:Suppress("UnstableApiUsage")

plugins {
    id("com.indramahkota.build.logic.convention.android-lib")
}

dependencies {
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
}
