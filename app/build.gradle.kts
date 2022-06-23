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
    implementation(Dependencies.Kotlin.kotlin)
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.material)
    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Dagger.dagger)
    implementation(Dependencies.Android.lifecycleViewModel)
    implementation(Dependencies.Android.lifecycleLivedata)
    implementation(Dependencies.Android.lifecycleRuntimeKtx)
    implementation(Dependencies.Kotlin.coroutinesCore)
    implementation(Dependencies.Kotlin.coroutinesAndroid)
    implementation(Dependencies.RxJava2.rxJava)
    implementation(Dependencies.RxJava2.rxAndroid)
    implementation(Dependencies.Google.gson)
    implementation(Dependencies.Retrofit2.retrofit)
    implementation(Dependencies.Retrofit2.rxJava2)
    implementation(Dependencies.Retrofit2.converterGson)
    implementation(Dependencies.OkHttp.okHttp)
    implementation(Dependencies.OkHttp.mockWebServer)
    implementation(Dependencies.Picasso.picasso)
    implementation(Dependencies.Hdodenhof.circleImageView)

    kapt(Dependencies.Dagger.daggerCompiler)

    testImplementation(TestDependencies.Kotlin.coroutinesTest)
    testImplementation(TestDependencies.JUnit.junit)
    testImplementation(TestDependencies.Mockito.mockito)
    testImplementation(TestDependencies.Mockito.mockitoKotlin2)
    testImplementation(TestDependencies.Android.coreTesting)

    androidTestImplementation(TestDependencies.Android.runner)
    androidTestImplementation(TestDependencies.Android.espressoCore)
    androidTestImplementation(TestDependencies.Android.coreTesting)
}