plugins {
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("io.realm.kotlin").version("1.10.0")
	kotlin("plugin.serialization").version("1.9.0")
}

android {
	namespace = "ru.veider.data"
	compileSdk = 34

	defaultConfig {
		minSdk = 26

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")

	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
		kotlinOptions {
			jvmTarget = "17"
		}
	}
}

dependencies {

	implementation("androidx.core:core-ktx:1.10.1")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.9.0")

	// Koin
	implementation("io.insert-koin:koin-androidx-compose:3.4.6")

	val ktorVersion = "2.3.3"
	implementation("io.ktor:ktor-client-core:$ktorVersion")
	implementation("io.ktor:ktor-client-android:$ktorVersion") // HTTP engine: The HTTP client used to perform network requests.
	implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion") // The serialization engine used to convert objects to and from JSON.
	implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
	implementation("io.ktor:ktor-client-logging:$ktorVersion") // Logging

	// Gson
	implementation("com.google.code.gson:gson:2.10.1")

	//realm
	implementation("io.realm.kotlin:library-base:1.10.2")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

	implementation(project(":app:domain"))
	implementation(project(":app:core"))

	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}