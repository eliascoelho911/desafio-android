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
    implementation(Dependencies.Retrofit2.converterGson)
    implementation(Dependencies.Google.gson)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.material)
    implementation(Dependencies.Android.navigation)
    implementation(Dependencies.Android.lifecycleRuntimeKtx)
    implementation(Dependencies.Android.recyclerView)
    implementation(Dependencies.Picasso.picasso)
    implementation(Dependencies.Picasso.transformations)
    implementation(Dependencies.Koin.android)

    testDependencies()
}