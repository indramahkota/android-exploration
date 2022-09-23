enableFeaturePreview("VERSION_CATALOGS")

// In settings.gradle you can add the repositories you want to add to the project
pluginManagement {
    repositories {
        includeBuild("build-logic")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // Register the AndroidX snapshot repository first so snapshots don't attempt (and fail)
        // to download from the non-snapshot repositories
        maven(url = "https://androidx.dev/snapshots/builds/8455591/artifacts/repository") {
            content {
                // The AndroidX snapshot repository will only have androidx artifacts, don't
                // bother trying to find other ones
                includeGroupByRegex("androidx\\..*")
            }
        }
        google()
        mavenCentral()
    }
}

// Iterate all files inside directory
fun File.children(): List<File> = listFiles()?.toList() ?: emptyList()

// Convert file to string with gradle project path separator
fun File.module(): String = toString()
    .replace(rootDir.toString(), "")
    .replace(File.separator, ":")

// Gradle multi-project paths
listOf("app", "core", "data").forEach { dir ->
    File("$rootDir/$dir")
        .walk(FileWalkDirection.BOTTOM_UP)
        .filter { file -> file.isDirectory }
        .filter { file -> file.children().any { it.name == "build.gradle.kts" } }
        .forEach { file ->
            file.module().let {
                include(it)
                project(it).projectDir = file
            }
        }
}

rootProject.name = "android-exploration"
