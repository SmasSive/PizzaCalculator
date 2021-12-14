plugins {
  id("com.android.library")
  id("kotlin-android")
}

android {
  compileSdk = 31

  defaultConfig {
    minSdk = 23
    targetSdk = 31

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
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
  implementation("androidx.annotation:annotation:1.3.0")

  implementation("io.insert-koin:koin-core:3.1.4")

  testImplementation("junit:junit:4.+")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
  testImplementation("io.insert-koin:koin-test:3.1.4")
  testImplementation("io.insert-koin:koin-test-junit4:3.1.4")
}