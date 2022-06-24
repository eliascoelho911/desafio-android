import com.android.build.gradle.LibraryExtension

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    moduleSetup()
}

dependencies {
    implementation(Dependencies.Android.material)
}