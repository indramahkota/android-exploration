<h1 align="center">Android Exploration</h1>

<div align="center">
<a href="https://github.com/indramahkota/android-exploration/actions/workflows/detekt-all.yml"><img src="https://github.com/indramahkota/android-exploration/actions/workflows/detekt-all.yml/badge.svg" alt="Scan with Detekt All"/></a> <a href="https://github.com/indramahkota/android-exploration/actions/workflows/assemble-release.yml"><img src="https://github.com/indramahkota/android-exploration/actions/workflows/assemble-release.yml/badge.svg" alt="Assemble Release"/></a>

<a href="https://github.com/indramahkota/android-exploration/blob/master/LICENSE"><img src="https://img.shields.io/github/license/indramahkota/android-exploration?color=blue" alt="LICENSE"/></a> <a href="https://github.com/indramahkota/android-exploration/stargazers"><img src="https://img.shields.io/github/stars/indramahkota/android-exploration" alt="GitHub Stars"/></a> <a href="#contributors"><img src="https://img.shields.io/badge/all_contributors-1-orange.svg?style=flat-square" alt="All Contributors"/></a>
</div>

<br/>

## 💻 Requirements

> You need to use [Android Studio](https://developer.android.com/studio) to run this project and github token for fetch some dependencies

```bash
# Clone this repository
$ git clone https://github.com/indramahkota/android-exploration.git

# Clone from Android Studio VCS
File -> New -> Project from Version Control -> GitHub -> Clone.
```

## 📝 Features

- CI/CD using [GitHub Actions](https://docs.github.com/en/actions/learn-github-actions/understanding-github-actions)
- Code smell analyses with [Kotlin Detekt](https://github.com/detekt/detekt)
- Android gradle project with [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- Some dependencies published on [GitHub Packages](https://docs.github.com/en/packages)
- Modern Android Development with 100% [Kotlin Language](https://kotlinlang.org/)
- UI/UX using material 3 and Jetpack Compose [Compose Material 3](https://developer.android.com/jetpack/androidx/releases/compose-material3)

## 💡Simplify Gradle build scripts

```kt
// Root project settings.gradle.kts
pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/indramahkota/build-logic-public/")
            credentials {
                username = "indramahkota"
                password = ""
            }
        }
    }
}
```

```kt
// Root project build.gradle.kts
@Suppress("StringLiteralDuplication")
plugins {
    id("com.indramahkota.build.logic.convention.detekt") version "0.0.5"

    id("com.indramahkota.build.logic.convention.android-config") version "0.0.5"
    id("com.indramahkota.build.logic.convention.android-lib") version "0.0.5" apply false
    id("com.indramahkota.build.logic.convention.android-app") version "0.0.5" apply false

    id("com.indramahkota.build.logic.convention.compose-config") version "0.0.5"
    id("com.indramahkota.build.logic.convention.compose-lib") version "0.0.5" apply false
    id("com.indramahkota.build.logic.convention.compose-app") version "0.0.5" apply false
}

val androidApplicationId by extra { "com.indramahkota.app.exploration" }
val androidVersionCode by extra { 1 }
val androidVersionName by extra { "0.0.0" }

// Initial configuration for subprojects
indramahkota {
    // Default $root/config/
    configsDir.set(file("config/"))

    // Default $root/build/reports/
    reportsDir.set(file("build/reports/"))

    // Default JavaVersion.VERSION_1_8
    jvmTarget.set(JavaVersion.VERSION_11)

    // Report directory: $reportsDir/detekt-reports/
    detekt {
        // Related with :detektDiff task
        checkOnlyDiffWithBranch("main") {
            fileExtensions = setOf(".kt", ".kts")
        }
    }

    android {
        minSdk.set(23)
        targetSdk.set(32)
    }

    // Report directory:
    // - $reportsDir/compose-reports/
    // - $reportsDir/compose-metrics/
    compose {
        compilerVersion.set("1.3.0")
        enableComposeCompilerMetrics.set(true)
        enableComposeCompilerReports.set(true)
    }
}
```

```kt
// In submodules project build.gradle.kts
plugins {
    id("com.indramahkota.build.logic.convention.android-app")
    id("com.indramahkota.build.logic.convention.compose-app")
}

//or

plugins {
    id("com.indramahkota.build.logic.convention.android-lib")
    id("com.indramahkota.build.logic.convention.compose-lib")
}
```

## License

```markdown
Copyright (c) 2021 Indra Mahkota

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
