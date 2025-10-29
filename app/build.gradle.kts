import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jacoco)
    alias(libs.plugins.serialization)
}

android {
    namespace = "br.com.jhonny.starlord"
    compileSdk = 36

    defaultConfig {
        applicationId = "br.com.jhonny.starlord"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments += "clearPackageData" to "true"
    }

    buildTypes {
        debug {
            enableAndroidTestCoverage = true
            enableUnitTestCoverage = true
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
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
    testImplementation(kotlin("test"))

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.navigation.testing)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

val jacocoExcludes = listOf(
    "**/R.class",
    "**/R$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/MainApplication.class",
    "**/di/MainModuleKt.class",
    "**/NavHostControllerKt.*",
    "**/dto/**",
    "**/vo/**",
    "**/state/**",
    "**/ui/**/provider/**",
    "**/ui/theme/**"
)

tasks.register<JacocoReport>("jacocoFullReport") {
    group = "verification"
    description = "Generates merged coverage report for unit and android tests"

    dependsOn("testDebugUnitTest", "connectedDebugAndroidTest")

    executionData.setFrom(
        files(
            fileTree(layout.buildDirectory.dir("jacoco")) {
                include("**/*.exec")
            },
            fileTree(layout.buildDirectory.dir("outputs/unit_test_code_coverage")) {
                include("**/*.exec")
            },
            fileTree(layout.buildDirectory.dir("outputs/code_coverage/debugAndroidTest/connected")) {
                include("**/*.ec")
            }
        )
    )

    val javaClasses = layout.buildDirectory.dir("intermediates/javac/debug/classes").map {
        fileTree(it) {
            exclude(jacocoExcludes)
        }
    }
    val kotlinClasses = layout.buildDirectory.dir("tmp/kotlin-classes/debug").map {
        fileTree(it) {
            exclude(jacocoExcludes)
        }
    }

    classDirectories.setFrom(files(javaClasses, kotlinClasses))
    sourceDirectories.setFrom(files("src/main/java", "src/main/kotlin"))

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}
