plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp") version "2.3.5"
}

android {
    namespace = "com.example.habit_trawcker"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.habit_trawcker"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true          // ← Detta måste vara true!
        // dataBinding = true       // bara om du använder Data Binding istället
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(platform("com.google.firebase:firebase-bom:34.9.0"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    val roomVersion = "2.8.4"

    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")      // viktigt för Flow och coroutines

    // KSP för Room-processor (detta är det som genererar koden)
    ksp("androidx.room:room-compiler:$roomVersion")

    //ksp("androidx.room:room-compiler:${room_version}")   // istället för kapt

    val lifecycleVersion = "2.10.0"

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")  //  LiveData
    implementation("androidx.activity:activity-ktx:1.9.3")  // för lifecycleScope i Activity
    implementation("androidx.fragment:fragment-ktx:1.8.4")  // fragment
}