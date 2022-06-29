plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    moduleSetup()
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":designsystem"))
    implementation(Dependencies.Picasso.picasso)
    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Google.gson)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.material)
    implementation(TestDependencies.Android.coreTesting)
    implementation(TestDependencies.Android.espressoCore)
    implementation(TestDependencies.OkHttp3.mockWebServer)
    implementation(TestDependencies.Kotlin.coroutinesTest)
}