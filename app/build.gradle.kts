import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kover)
    alias(libs.plugins.serialization)
}

android {
    namespace = "br.com.jhonny.startlord"
    compileSdk = 36

    defaultConfig {
        applicationId = "br.com.jhonny.startlord"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }

        explicitApi()
    }

    buildFeatures {
        compose = true
    }

    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

kover {
    reports {
        filters {
            excludes {
                classes(
                    "br.com.jhonny.startlord.MainApplication",
                    "br.com.jhonny.startlord.di.MainModuleKt",
                    "br.com.jhonny.startlord.*.dto.*",
                    "br.com.jhonny.startlord.*.vo.*",
                    "br.com.jhonny.startlord.*.state.*",
                )
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    implementation(project.dependencies.platform(libs.di.koin.bom))
    implementation(libs.di.koin.android)
    implementation(libs.di.koin.androidx.compose)

    implementation(libs.img.coil.compose)
    implementation(libs.img.coil.network.okhttp)

    implementation(libs.http.retrofit)
    implementation(libs.http.retrofit.converter.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.navigation)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    testImplementation(libs.junit)
    testImplementation(libs.mockk.android)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
