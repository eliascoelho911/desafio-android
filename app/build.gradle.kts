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
    implementation(project(":core"))

    implementation(Dependencies.Kotlin.kotlin)
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Android.lifecycleViewModel)
    implementation(Dependencies.Android.lifecycleLivedata)
    implementation(Dependencies.Android.lifecycleRuntimeKtx)
    implementation(Dependencies.Kotlin.coroutinesCore)
    implementation(Dependencies.Kotlin.coroutinesAndroid)
    implementation(Dependencies.Picasso.picasso)
    implementation(Dependencies.Hdodenhof.circleImageView)

    testImplementation(TestDependencies.Kotlin.coroutinesTest)
    testImplementation(TestDependencies.JUnit.junit)
    testImplementation(TestDependencies.Android.coreTesting)

    androidTestImplementation(TestDependencies.Android.runner)
    androidTestImplementation(TestDependencies.Android.espressoCore)
    androidTestImplementation(TestDependencies.Android.coreTesting)
}