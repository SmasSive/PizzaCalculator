plugins {
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.kotlinAndroid)
}

val versionNum: String? by project
val versionMajor = properties["smassive.version.major"].toString().toInt()
val versionMinor = properties["smassive.version.minor"].toString().toInt()
val versionPatch = properties["smassive.version.patch"].toString().toInt()

fun versionCode(): Int {
  versionNum?.let {
    return (versionMajor * 1000000) + (versionMinor * 1000) + it.toInt()
  } ?: return 1
}

android {
  compileSdk = properties["smassive.android.compileSdk"].toString().toInt()
  namespace = "com.smassive.pizzacalculator"

  defaultConfig {
    applicationId = "com.smassive.pizzacalculator"
    minSdk = properties["smassive.android.minSdk"].toString().toInt()
    versionCode = versionCode()
    versionName = "$versionMajor.$versionMinor.$versionPatch"

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
    kotlinCompilerExtensionVersion = "1.5.5"
  }
}

dependencies {
  implementation(project(":domain"))

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.ui)
  implementation(libs.androidx.material)
  implementation(libs.androidx.ui.tooling)
  implementation(libs.androidx.runtime)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.navigation.compose)

  implementation(libs.koin.android)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
}
