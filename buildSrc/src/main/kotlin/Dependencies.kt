object Dependencies {
    object Kotlin {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.kotlin}"
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
    }

    object Android {
        const val coreKtx = "androidx.core:core-ktx:${Versions.Android.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:$${Versions.Android.appCompat}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.Android.fragmentKtx}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayout}"
        const val material = "com.google.android.material:material:${Versions.Android.material}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.lifecycle}"
        const val lifecycleLivedata =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Android.lifecycle}"
        const val lifecycleRuntimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.lifecycle}"
        const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.Android.navigation}"
    }

    object Facebook {
        const val shimmer = "com.facebook.shimmer:shimmer:${Versions.Facebook.shimmer}"
    }

    object Koin {
        const val android = "io.insert-koin:koin-android:${Versions.Koin.koin}"
        const val core = "io.insert-koin:koin-core:${Versions.Koin.koin}"
    }

    object Google {
        const val gson = "com.google.code.gson:gson:${Versions.Google.gson}"
    }

    object Retrofit2 {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Retrofit2.retrofit}"
        const val converterGson =
            "com.squareup.retrofit2:converter-gson:${Versions.Retrofit2.retrofit}"
    }

    object OkHttp {
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.OkHttp3.okhttp}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.OkHttp3.okhttp}"
    }

    object Picasso {
        const val picasso = "com.squareup.picasso:picasso:${Versions.Picasso.picasso}"
    }

    object Hdodenhof {
        const val circleImageView = "de.hdodenhof:circleimageview:${Versions.Hdodenhof.circleImageView}"
    }
}