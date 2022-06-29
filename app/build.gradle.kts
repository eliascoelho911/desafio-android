plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    appSetup()

    viewBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(project(":designsystem"))
    implementation(project(":contacts"))
    implementation(project(":core:core"))

    implementation(Dependencies.Kotlin.kotlin)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Koin.android)
}