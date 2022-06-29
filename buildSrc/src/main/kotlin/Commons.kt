import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun BaseAppModuleExtension.appSetup() {
    projectDefaultConfig()

    defaultConfig {
        applicationId = "com.picpay.desafio.android"
        versionCode = 1
        versionName = "1.0"
    }

    projectBuildTypes()
    projectCompileOptions()
}

private fun TestedExtension.projectDefaultConfig() {
    compileSdkVersion = ProjectConfig.compileSdkVersion

    defaultConfig {
        minSdkPreview = ProjectConfig.minSdkVersion
        targetSdkPreview = ProjectConfig.targetSdkVersion

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

private fun TestedExtension.projectBuildTypes() {
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
            buildConfigField("String",
                "BASE_URL",
                "\"https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/\"")
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
            buildConfigField("String",
                "BASE_URL",
                "\"https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/\"")
        }
    }
}

private fun TestedExtension.projectCompileOptions() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

fun LibraryExtension.moduleSetup() {
    viewBinding {
        isEnabled = true
    }

    projectDefaultConfig()
    projectBuildTypes()
    projectCompileOptions()
    setupSharedTests()
}

private fun LibraryExtension.setupSharedTests() {
    sourceSets {
        val sharedTestDir = "src/sharedTest/"
        val sharedTestSourceDir = "${sharedTestDir}java"
        val sharedTestResourceDir = "${sharedTestDir}resources"
        named("test") {
            java.srcDir(sharedTestSourceDir)
            resources.srcDir(sharedTestResourceDir)
        }
        named("androidTest") {
            java.srcDir(sharedTestSourceDir)
            resources.srcDir(sharedTestResourceDir)
        }
    }
}

fun DependencyHandlerScope.testDependencies() {
    "testImplementation"(TestDependencies.Robolectric.robolectric)
    "testImplementation"(TestDependencies.JUnit.junit)
    "testImplementation"(TestDependencies.Android.junitExt)
    "testImplementation"(TestDependencies.Android.espressoCore)
    "testImplementation"(TestDependencies.Android.coreKtx)
    "testImplementation"(TestDependencies.Android.coreTesting)
    "testImplementation"(TestDependencies.Kotlin.coroutinesTest)
    "testImplementation"(TestDependencies.Android.runner)
    "testImplementation"(TestDependencies.OkHttp3.mockWebServer)
    "testImplementation"(TestDependencies.MockK.mockK)
    "testImplementation"(TestDependencies.Koin.koinTest)
    "testImplementation"(TestDependencies.Koin.koinTestJunit4)
    "debugImplementation"(TestDependencies.Android.fragmentTesting)
    "androidTestImplementation"(TestDependencies.OkHttp3.mockWebServer)
    "androidTestImplementation"(TestDependencies.Android.espressoCore)
    "androidTestImplementation"(TestDependencies.Android.runner)
    "androidTestImplementation"(TestDependencies.Android.junitExt)
    "androidTestImplementation"(TestDependencies.Android.coreKtx)
    "androidTestImplementation"(TestDependencies.Android.coreTesting)
    "androidTestImplementation"(TestDependencies.MockK.android)
    "androidTestImplementation"(TestDependencies.Robolectric.annotations)
}
