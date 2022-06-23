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
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayout}"
        const val material = "com.google.android.material:material:${Versions.Android.material}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.lifecycle}"
        const val lifecycleLivedata =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Android.lifecycle}"
        const val lifecycleRuntimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.lifecycle}"
    }

    object Koin {
        const val android = "io.insert-koin:koin-android:${Versions.Koin.koin}"
    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.Dagger.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.Dagger.dagger}"
    }

    object RxJava2 {
        const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.RxJava2.rxjava}"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.RxJava2.rxandroid}"
    }

    object Google {
        const val gson = "com.google.code.gson:gson:${Versions.Google.gson}"
    }

    object Retrofit2 {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Retrofit2.retrofit}"
        const val rxJava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.Retrofit2.retrofit}"
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