plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    moduleSetup()
}

dependencies {
    implementation(Dependencies.Google.gson)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.material)
    implementation(Dependencies.Retrofit2.retrofit)
    implementation(Dependencies.Retrofit2.converterGson)
    implementation(Dependencies.OkHttp.okHttp)
    implementation(Dependencies.Koin.core)
}