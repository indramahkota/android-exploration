// In settings.gradle you can add the repositories you want to add to the project
pluginManagement {
    repositories {
        maven(url = "https://maven.pkg.github.com/indramahkota/build-logic-public/") {
            name = "GitHubPackages"
            credentials {
                username = providers.gradleProperty("github.username").orNull
                    ?: System.getenv("GITHUB_USERNAME")
                password = providers.gradleProperty("github.token").orNull
                    ?: System.getenv("GITHUB_TOKEN")
            }
        }
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(url = "https://maven.pkg.github.com/indramahkota/version-catalog-public/") {
            credentials {
                username = providers.gradleProperty("github.username").orNull
                    ?: System.getenv("GITHUB_USERNAME")
                password = providers.gradleProperty("github.token").orNull
                    ?: System.getenv("GITHUB_TOKEN")
            }
        }
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from("com.indramahkota.gradle.version:catalog-android:0.0.6")
        }
        create("indra") {
            from("com.indramahkota.gradle.version:catalog-indramahkota:0.0.6")
        }
    }
}

fun File.module(rootDir: File): String? =
    parent?.toString()?.replace(rootDir.toString(), "")?.replace(File.separator, ":")

val fileName = "build.gradle.kts"
val dirs = setOf("app", "core", "data", "features")
dirs.parallelStream().forEach { dir ->
    File(rootDir, dir).walkBottomUp()
        .filter { it.name == fileName && it.isFile }
        .forEach { file ->
            file.module(rootDir)?.let {
                include(it)
                project(it).projectDir = file.parentFile
            }
        }
}

rootProject.name = "android-exploration"
