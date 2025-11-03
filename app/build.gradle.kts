import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kover)
    alias(libs.plugins.serialization)
}

android {
    namespace = "br.com.jhonny.starlord"
    testNamespace = "br.com.jhonny.starlord.test"
    compileSdk = 36

    defaultConfig {
        applicationId = "br.com.jhonny.starlord"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "br.com.jhonny.starlord.KoinTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_21
        }

        explicitApi()
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    testOptions {
        animationsDisabled = true

        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }

        unitTests.all {
            val availableProcessors = Runtime.getRuntime().availableProcessors() / 2
            println("Running test on max parallel forks: $availableProcessors")
            it.maxParallelForks = availableProcessors
        }
    }

    packaging {
        resources {
            excludes += setOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
                "META-INF/NOTICE.md"
            )
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
    implementation(libs.androidx.compose.ui.icons)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    implementation(project.dependencies.platform(libs.di.koin.bom))
    implementation(libs.di.koin.android)
    implementation(libs.di.koin.androidx.compose)

    implementation(libs.img.coil.compose)
    implementation(libs.img.coil.network.okhttp)

    implementation(libs.http.client.logging.interceptor)
    implementation(libs.http.retrofit)
    implementation(libs.http.retrofit.converter.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.navigation)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    testImplementation(libs.flow.test.turbine)
    testImplementation(libs.junit)
    testImplementation(libs.mockk.android)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.robolectric)
    testImplementation(kotlin("test"))
    testImplementation(libs.androidx.compose.ui.test.junit4)
    testImplementation(libs.androidx.navigation.testing)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.di.koin.test)
    androidTestImplementation(libs.di.koin.junit4)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

kover {
    reports {
        filters {
            excludes {
                packages(
                    "*.ui.theme",
                    "*.di",
                    "*.ui.preview",
                    "*.state",
                    "*.vo",
                    "*.dto",
                )
                classes(
                    "*.Generated*",
                    "*.BuildConfig*",
                    "*ComposableSingletons*",

                    "*.MainActivity",
                    "*.MainApplication",
                    "*PreviewProvider*",
                    "*.NavHostControllerExtKt",
                )
            }
        }
    }
}
