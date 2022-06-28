object TestDependencies {

    object Kotlin {
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.coroutines}"
    }

    object JUnit {
        const val junit = "junit:junit:${Versions.JUnit.junit}"
    }

    object Android {
        const val coreTesting = "androidx.arch.core:core-testing:${Versions.Android.coreTesting}"
        const val runner = "androidx.test:runner:${Versions.Android.testRunner}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Android.espresso}"
        const val junitExt = "androidx.test.ext:junit:${Versions.Android.junitExt}"
        const val coreKtx = "androidx.test:core-ktx:${Versions.Android.coreKtxTest}"
    }

    object Robolectric {
        const val robolectric = "org.robolectric:robolectric:${Versions.Robolectric.robolectric}"
        const val annotations = "org.robolectric:annotations:${Versions.Robolectric.robolectric}"
    }
}