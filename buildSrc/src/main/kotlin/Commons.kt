import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion

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

fun LibraryExtension.moduleSetup() {
    viewBinding {
        isEnabled = true
    }

    projectDefaultConfig()
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