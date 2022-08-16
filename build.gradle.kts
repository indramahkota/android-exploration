// We want to use new APIs
@file:Suppress("UnstableApiUsage")

@Suppress("StringLiteralDuplication")
plugins {
    // indramahkota custom plugin
    id("com.indramahkota.build.logic.convention.android-config") version "0.0.0" apply true
    id("com.indramahkota.build.logic.convention.android-app") version "0.0.0" apply false
    id("com.indramahkota.build.logic.convention.android-lib") version "0.0.0" apply false

    // plugin id: kotlin-kapt
    id("org.jetbrains.kotlin.kapt") version "1.6.21" apply false
    // plugin id: kotlin-parcelize
    id("org.jetbrains.kotlin.plugin.parcelize") version "1.6.21" apply false
    // plugin id: dagger.hilt.android.plugin
    id("com.google.dagger.hilt.android") version "2.42" apply false
    // plugin id: androidx.navigation.safeargs.kotlin
    id("androidx.navigation.safeargs.kotlin") version "2.4.2" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
