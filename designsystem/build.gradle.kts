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
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Facebook.shimmer)
}