plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.towerofapp.getitdone"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.towerofapp.getitdone"
        minSdk = 24
        targetSdk = 34
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // room dependency
    val room_version = "2.7.1"
        // Adds Room runtime library for database operations.
    implementation("androidx.room:room-runtime:$room_version")
        // Uses KSP (Kotlin Symbol Processing) to generate Room-related code for Kotlin.
    ksp("androidx.room:room-compiler:$room_version")
        // Needed if you're using Java or mixing Java with Kotlin in the project.
    annotationProcessor("androidx.room:room-compiler:$room_version")
        // Provides Kotlin extensions (coroutines, Flow support) for Room.
    implementation("androidx.room:room-ktx:$room_version")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}