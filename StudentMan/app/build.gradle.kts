plugins {
  alias(libs.plugins.android.application) // Plugin for Android application
  alias(libs.plugins.kotlin.android) // Kotlin Android plugin
  alias(libs.plugins.kotlin.parcelize) // Ensure this line is present for Parcelize support
}

android {
  namespace = "vn.edu.hust.studentman"
  compileSdk = 35 // Ensure compile SDK is set correctly

  defaultConfig {
    applicationId = "vn.edu.hust.studentman"
    minSdk = 24 // Minimum supported SDK version
    targetSdk = 34 // Target SDK version
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Test runner
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    // Specify Java compatibility versions
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = "11" // JVM target version for Kotlin
  }
}

dependencies {
  // Core AndroidX dependencies
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.activity)
  implementation(libs.androidx.constraintlayout)

  // Testing dependencies
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}
