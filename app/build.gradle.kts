plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.buiducha.teamtracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.buiducha.teamtracker"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.6")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.android.material:material:1.11.0")
    implementation("com.google.firebase:firebase-messaging:23.4.0")
    implementation("com.android.volley:volley:1.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation ("androidx.compose.material3:material3:1.1.2")
    implementation ("androidx.compose.material3:material3-window-size-class:1.1.2")
    implementation ("androidx.compose.material:material-icons-extended:1.5.4")
    implementation ("androidx.compose.material:material:1.1.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.5.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    implementation ("androidx.navigation:navigation-compose:2.7.6")

    implementation ("com.github.bumptech.glide:compose:1.0.0-beta01")

    implementation("io.coil-kt:coil-compose:2.4.0")

    // Stream SDK
    implementation("io.getstream:stream-chat-android-compose:6.0.8")
    implementation("io.getstream:stream-chat-android-offline:6.0.8")
    implementation ("com.auth0:java-jwt:3.18.1")

    implementation("com.google.accompanist:accompanist-permissions:0.32.0")

    // qr code
    implementation ("com.google.zxing:core:3.5.1")
    implementation("com.journeyapps:zxing-android-embedded:4.1.0")
    implementation ("com.google.android.gms:play-services-code-scanner:16.1.0")
    val camerax_version = "1.0.2"
    implementation ("androidx.camera:camera-core:${camerax_version}")
    implementation ("androidx.camera:camera-camera2:${camerax_version}")
    implementation ("androidx.camera:camera-lifecycle:${camerax_version}")
    implementation ("androidx.camera:camera-view:1.0.0-alpha29")

    //Barcode
    implementation ("com.google.mlkit:barcode-scanning:17.2.0")

    //Camera Permission
    implementation ("com.google.accompanist:accompanist-permissions:0.19.0")


}