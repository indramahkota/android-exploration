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
      from("com.indramahkota.gradle.version:catalog-android:0.2.1")
    }
    create("indra") {
      from("com.indramahkota.gradle.version:catalog-indramahkota:0.4.5")
    }
  }
}

rootProject.name = "android-exploration"

include(":app:exploration")
include(":app:designsystem")
include(":core:designsystem")
include(":core:media")
include(":core:navigation")
include(":core:ui")
include(":data:network")
include(":data:github")
include(":feature:designsystem")
include(":feature:homebase")
include(":feature:profile")
