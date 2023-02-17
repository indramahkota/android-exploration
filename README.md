<h1 align="center">Android Exploration</h1>

<div align="center">
<a href="https://github.com/indramahkota/android-exploration/actions/workflows/detekt-all.yml"><img src="https://github.com/indramahkota/android-exploration/actions/workflows/detekt-all.yml/badge.svg" alt="Scan with Detekt"/></a> <a href="https://github.com/indramahkota/android-exploration/actions/workflows/assemble-release.yml"><img src="https://github.com/indramahkota/android-exploration/actions/workflows/assemble-release.yml/badge.svg" alt="Assemble Release"/></a>

<a href="https://github.com/indramahkota/android-exploration/blob/master/LICENSE"><img src="https://img.shields.io/github/license/indramahkota/android-exploration?color=blue" alt="LICENSE"/></a> <a href="https://github.com/indramahkota/android-exploration/stargazers"><img src="https://img.shields.io/github/stars/indramahkota/android-exploration" alt="GitHub Stars"/></a> <a href="#contributors"><img src="https://img.shields.io/badge/all_contributors-1-orange.svg?style=flat" alt="All Contributors"/></a>
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

</br>

## 📝 Features

- CI/CD using [GitHub Actions](https://docs.github.com/en/actions/learn-github-actions/understanding-github-actions)
- Code smell analysis with [Kotlin Detekt](https://github.com/detekt/detekt)
- Android gradle project with [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- Some dependencies published on [GitHub Packages](https://docs.github.com/en/packages)
- Modern Android Development with 100% [Kotlin Language](https://kotlinlang.org/)
- UI/UX using material 3 and Jetpack Compose [Compose Material 3](https://developer.android.com/jetpack/androidx/releases/compose-material3)

</br>

## ⬇️ Download the APK
- Open the [Assemble Release Action](https://github.com/indramahkota/android-exploration/actions/workflows/assemble-release.yml)
- Click the latest success running task

<div align="center">
<a href="https://github.com/indramahkota/android-exploration/actions/workflows/assemble-release.yml"><img src="https://user-images.githubusercontent.com/34052126/193442434-1a36f7d2-a378-4230-9315-c044b030320f.png" alt="Download the APK"/></a>
</div>

</br>

## 💡Simplify Gradle build scripts

```kt
// Root project settings.gradle.kts
enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        maven(url = "https://maven.pkg.github.com/indramahkota/build-logic-public/")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(url = "https://maven.pkg.github.com/indramahkota/build-logic-public/")
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from("com.indramahkota.build.libs.versions:versions-stack:0.0.7")
        }
    }
}

plugins {
    id("com.indramahkota.build.logic.convention.settings") version "0.3.1"
}
```

```kt
// Root project build.gradle.kts
plugins {
    id("com.indramahkota.build.logic.convention.detekt")
    id("com.indramahkota.build.logic.convention.android-config")
    id("com.indramahkota.build.logic.convention.compose-config")
    id("com.indramahkota.build.logic.convention.publish-config")
}

val androidApplicationId by extra { "com.indramahkota.app.exploration" }
val androidVersionCode by extra { 1 }
val androidVersionName by extra { "0.0.0" }

// Initial configuration for subprojects
indramahkota {
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
        targetSdk.set(33)
    }

    // Report directory:
    // - $reportsDir/compose-reports/
    // - $reportsDir/compose-metrics/
    compose {
        // https://developer.android.com/jetpack/androidx/releases/compose
        // compiler and runtime is mandatory property
        // default is v1.3.2 and v1.3.0-rc01
        compilerVersion.set("1.4.2")
        runtimeVersion.set("1.3.3")
        enableComposeCompilerMetrics.set(true)
        enableComposeCompilerReports.set(true)
    }

    // Set maven pom for all sub projects
    publishing {
        pom {
            setGitHubProject("indramahkota/android-exploration")

            licenses {
                mit()
            }

            developers {
                developer(
                    id = "indramahkota",
                    name = "Indra Mahkota",
                    email = "indramahkota1@gmail.com"
                )
            }
        }
    }
}
```

```kt
// In submodules project build.gradle.kts
plugins {
    // Automatically apply android plugin
    id("com.indramahkota.build.logic.convention.compose-app")
    id("com.indramahkota.build.logic.convention.hilt")
}

//or

plugins {
    // Automatically apply android plugin
    id("com.indramahkota.build.logic.convention.compose-lib")
    id("com.indramahkota.build.logic.convention.hilt")
    id("com.indramahkota.build.logic.convention.publishing")
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
