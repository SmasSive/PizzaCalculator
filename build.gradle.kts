// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath(libs.gradle)
    classpath(libs.kotlin.gradle.plugin)

    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle.kts files
  }
}

plugins {
  alias(libs.plugins.androidApplication).apply(false)
  alias(libs.plugins.androidLibrary).apply(false)
  alias(libs.plugins.kotlinAndroid).apply(false)
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}