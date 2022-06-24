plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    moduleSetup()
}

dependencies {
    implementation(project(":designsystem"))
    implementation(project(":core"))
    implementation(Dependencies.Retrofit2.retrofit)
    implementation(Dependencies.Google.gson)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.material)
    implementation(Dependencies.Android.navigation)
    implementation(Dependencies.Android.lifecycleRuntimeKtx)
    implementation(Dependencies.Picasso.picasso)
    implementation(Dependencies.Koin.android)
}