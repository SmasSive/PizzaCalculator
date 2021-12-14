plugins {
  id("com.android.application")
  id("kotlin-android")
}

android {
  compileSdk = 31

  defaultConfig {
    applicationId = "com.smassive.pizzacalculator"
    minSdk = 23
    targetSdk = 31
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
  }
}

dependencies {
  implementation(project(":domain"))

  implementation("androidx.core:core-ktx:1.7.0")
  implementation("androidx.appcompat:appcompat:1.4.0")
  implementation("com.google.android.material:material:1.4.0")
  implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
  implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
  implementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
  implementation("androidx.compose.runtime:runtime:${rootProject.extra["compose_version"]}")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
  implementation("androidx.activity:activity-compose:${rootProject.extra["compose_version"]}")
  implementation("androidx.navigation:navigation-compose:2.4.0-beta02")

  implementation("io.coil-kt:coil-svg:1.4.0")
  implementation("com.google.accompanist:accompanist-coil:0.15.0")

  implementation("io.insert-koin:koin-android:3.1.4")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
}
