plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    //todo: Encontrar uma forma de referenciar o buildSrc
    implementation("com.android.tools.build:gradle:7.2.1")
}