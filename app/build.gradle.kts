plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    //    id("com.google.gms.google-services")
}

android {
    namespace = "com.prototype.gymshark"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.prototype.gymshark"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // ---------------- Core Android ----------------
    implementation(libs.androidx.core.ktx) // Core KTX extensions
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2") // Lifecycle runtime
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2") // ViewModel KTX
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2") // ViewModel Compose
    implementation("androidx.activity:activity-compose:1.8.2") // Compose Activity

    // ---------------- Jetpack Compose ----------------
    implementation(platform(libs.androidx.compose.bom)) // Compose BOM
    implementation(libs.androidx.compose.ui) // Compose UI
    implementation(libs.androidx.compose.ui.graphics) // Compose Graphics
    implementation(libs.androidx.compose.ui.tooling.preview) // UI Preview tooling
    implementation(libs.androidx.compose.material3) // Material3 components
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.ui.graphics) // Compose layouts
    debugImplementation(libs.androidx.compose.ui.tooling) // UI tooling for debugging
    debugImplementation(libs.androidx.compose.ui.test.manifest) // Test manifest

    // ---------------- Navigation ----------------
    implementation(libs.androidx.navigation.runtime.ktx) // Navigation runtime
    implementation("androidx.navigation:navigation-compose:2.7.2") // Compose Navigation

    // ---------------- WorkManager & Hilt ----------------
    implementation(libs.androidx.work.runtime.ktx) // WorkManager
    implementation(libs.androidx.hilt.work) // Hilt + WorkManager
    implementation("com.google.dagger:hilt-android:2.51.1") // Hilt DI
    kapt("com.google.dagger:hilt-android-compiler:2.51.1") // Hilt compiler (KAPT)
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0") // Hilt Compose navigation
    implementation("androidx.hilt:hilt-work:1.2.0") // Hilt + WorkManager helper
    kapt("androidx.hilt:hilt-compiler:1.2.0") // Hilt compiler

    // ---------------- Networking ----------------
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0") // Moshi converter
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Gson converter
    implementation("com.squareup.moshi:moshi:1.15.0") // Moshi core
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0") // Moshi Kotlin
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.0") // Moshi codegen
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0") // Logging interceptor

    // ---------------- Coroutines ----------------
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3") // Core coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3") // Android coroutines

    // ---------------- Firebase ----------------
    implementation(platform("com.google.firebase:firebase-bom:33.5.1")) // Firebase BOM
    implementation("com.google.firebase:firebase-messaging-ktx") // Messaging
    implementation("com.google.firebase:firebase-config-ktx") // Remote Config
    implementation("com.google.firebase:firebase-storage-ktx:20.3.0") // Firebase Storage

    // ---------------- OneSignal ----------------
    implementation("com.onesignal:OneSignal:5.1.17") // Push notifications

    // ---------------- Datastore ----------------
    implementation("androidx.datastore:datastore-preferences:1.0.0") // Preferences datastore

    // ---------------- Splash & UI ----------------
    implementation("androidx.core:core-splashscreen:1.0.1") // Splash screen
    implementation("com.google.accompanist:accompanist-swiperefresh:0.30.1") // Swipe refresh

    // ---------------- Maps & Location ----------------
    implementation("com.google.android.gms:play-services-location:21.3.0") // Location services
    implementation("com.google.android.gms:play-services-maps:19.0.0") // Maps
    implementation("com.google.maps.android:maps-compose:4.4.1") // Compose Maps


    // ---------------- Logging ----------------
    implementation("com.jakewharton.timber:timber:5.0.1") // Logging

    // ---------------- UI Libraries ----------------
    implementation("androidx.compose.material:material:1.6.0") // Material UI
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.0") // UI preview tooling

    // ---------------- Glassmorphism ----------------
    implementation("dev.chrisbanes.haze:haze-jetpack-compose:0.4.1")

    // ---------------- Coil (Images / Videos) ----------------
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("io.coil-kt:coil-gif:2.6.0")

    // ---------------- Testing ----------------
    testImplementation(libs.junit) // JUnit
    androidTestImplementation(libs.androidx.junit) // AndroidX JUnit
    androidTestImplementation(libs.androidx.espresso.core) // Espresso
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Compose BOM for tests
    androidTestImplementation(libs.androidx.compose.ui.test.junit4) // Compose test


}