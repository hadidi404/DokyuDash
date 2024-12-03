plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id ("com.google.gms.google-services")
}

android {
    namespace = "com.example.dokyudashprototype"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.dokyudashprototype"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.firestore)
    implementation(libs.play.services.maps)
    implementation(libs.firebase.database)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.google.firebase:firebase-auth:22.0.0") // Firebase Authentication
    implementation("com.google.firebase:firebase-storage:20.2.1") // Firebase Storage
    implementation("com.journeyapps:zxing-android-embedded:4.3.0") // Latest stable version
    implementation("com.google.zxing:core:3.5.3")
    implementation ("com.github.bumptech.glide:glide:4.12.0")  // Check for the latest version
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")  // For Glide's annotations




}


