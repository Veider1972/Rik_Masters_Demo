pluginManagement {
	repositories {
		google()
		mavenCentral()
		gradlePluginPortal()
		maven(url="https://kotlin.bintray.com/kotlinx")
	}
}
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
	}
}

rootProject.name = "Rik Masters Test"
include(":app")
include(":app:data")
include(":app:domain")
include(":app:core")
include(":app:usecases")
