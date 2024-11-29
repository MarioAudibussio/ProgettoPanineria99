plugins {
    id("com.google.gms.google-services")

    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.schedacibo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.schedacibo"
        minSdk = 29
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

    // Dipendenze di Google Maps
    implementation("com.google.android.gms:play-services-maps:18.1.0")

    // Altre dipendenze dal file libs.versions.toml
    implementation(platform(libs.firebase.bom))
    implementation(libs.picasso)
    implementation(libs.firebase.database)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.squareupretrofit)
    implementation(libs.converter.json)
    implementation(libs.squareupretrofit)
    implementation(libs.converter.json)
    implementation(libs.androidx.recyclerview)
    // Dipendenze di testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
