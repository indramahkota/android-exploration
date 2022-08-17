// We want to use new APIs
@file:Suppress("UnstableApiUsage")

@Suppress("StringLiteralDuplication")
plugins {
    // plugin id: kotlin-android
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
    // plugin id: com.android.application
    id("com.android.application") version "7.2.1" apply false
    // plugin id: com.android.library
    id("com.android.library") version "7.2.1" apply false
    // plugin id: dagger.hilt.android.plugin
    id("com.google.dagger.hilt.android") version "2.42" apply false

    // indramahkota custom plugin
    id("com.indramahkota.build.logic.convention.android-config") version "0.0.0" apply true
    id("com.indramahkota.build.logic.convention.android-app") version "0.0.0" apply false
    id("com.indramahkota.build.logic.convention.android-lib") version "0.0.0" apply false
    id("com.indramahkota.build.logic.convention.detekt") version "0.0.0" apply true
}

// Configure subprojects from root project
indramahkota {
    configsDir.set(file("/config/"))
    reportsDir.set(file("/build/reports/"))

    android {
        minSdk.set(23)
        targetSdk.set(32)
    }
}
