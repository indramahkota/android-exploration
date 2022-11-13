plugins {
    id("com.indramahkota.build.logic.convention.compose-lib")
}

android {
    namespace = "com.indramahkota.app.core.design.system"
    testNamespace = "com.indramahkota.app.core.design.system.test"
}

dependencies {
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.foundation)
    implementation(libs.compose.foundation.layout)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.ui.util)
    implementation(libs.compose.runtime.livedata)
    implementation(libs.compose.runtime.tracing)
}
