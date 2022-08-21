// We want to use new APIs
@file:Suppress("UnstableApiUsage")

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
    jvmTarget.set(JavaVersion.VERSION_1_8)

    android {
        /**
         * CONFIGURATION
         * -----------------------
         * kotlin-android
         * kotlin-kapt
         * kotlin-parcelize
         * app: com.android.application
         * lib: com.android.library
         * jetpack compose
         * -----------------------
         *
         * IMPLEMENTATION
         * -----------------------
         * */
        minSdk.set(23)
        targetSdk.set(32)
    }
}
