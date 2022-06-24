import com.android.build.gradle.LibraryExtension

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    moduleSetup()
}

dependencies {
    implementation(project(":designsystem"))
    implementation(Dependencies.Retrofit2.retrofit)
    implementation(Dependencies.Google.gson)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.material)
}