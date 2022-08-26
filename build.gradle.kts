@Suppress("StringLiteralDuplication")
plugins {
    id("com.indramahkota.build.logic.convention.android-lib") version "0.0.0" apply false
    id("com.indramahkota.build.logic.convention.android-app") version "0.0.0" apply false
    id("com.indramahkota.build.logic.convention.android-config") version "0.0.0"
    id("com.indramahkota.build.logic.convention.detekt") version "0.0.0"
}

// Configure subprojects from root project
indramahkota {
    // Default $root/config/
    configsDir.set(file("config/"))

    // Default $root/build/reports/
    reportsDir.set(file("build/reports/"))

    // Default JavaVersion.VERSION_1_8
    jvmTarget.set(JavaVersion.VERSION_11)

    /**
     * CONFIGURATION KOTLIN
     * -----------------------
     * kotlin stdlib-jdk8 1.7.10
     * freeCompilerArgs opt-in
     * useJunitPlatform for test
     * kotlinx-coroutines-core
     * kotlinx-coroutines-android
     * -----------------------
     *
     *
     * IMPLEMENTATION ANDROID
     * -----------------------
     * kotlin-kapt
     * kotlin-android
     * kotlin-parcelize
     * com.android.library
     * com.android.application
     * -----------------------
     *
     *
     * IMPLEMENTATION COMPOSE
     * -----------------------
     * not implemented by default
     * activity-compose
     * constraintlayout-compose
     * -----------------------
     * */
    android {
        minSdk.set(23)
        targetSdk.set(32)

        usingCompose.set(true)
        composeCompilerVersion.set("1.3.0-rc02")
    }
}
