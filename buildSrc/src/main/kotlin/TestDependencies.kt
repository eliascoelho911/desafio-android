object TestDependencies {

    object Kotlin {
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.coroutines}"
    }

    object JUnit {
        const val junit = "junit:junit:${Versions.JUnit.junit}"
    }

    object Mockito {
        const val mockito = "org.mockito:mockito-core:${Versions.Mockito.mockito}"
        const val mockitoKotlin2 = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.Mockito.mockitoKotlin}"
    }

    object Android {
        const val coreTesting = "androidx.arch.core:core-testing:${Versions.Android.coreTesting}"
        const val runner = "androidx.test:runner:${Versions.Android.testRunner}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Android.espresso}"
        const val coreKtx = "androidx.test:core-ktx:${Versions.Android.coreKtxTest}"
    }
}