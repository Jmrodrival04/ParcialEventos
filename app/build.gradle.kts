// build.gradle.kts (Nivel de Módulo `app`)

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services") // Plugin de Google Services
}

android {
    namespace = "com.example.parcialeventos"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.parcialeventos"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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
    // Firebase BoM (gestiona automáticamente las versiones de Firebase)
    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))

    // Agrega las dependencias de Firebase que deseas usar en tu proyecto
    implementation("com.google.firebase:firebase-analytics")
    // Puedes agregar otras dependencias de Firebase, como Auth, Firestore, etc.
    // Ejemplo:
    // implementation("com.google.firebase:firebase-auth")
    // implementation("com.google.firebase:firebase-firestore")

    // Otras dependencias de tu proyecto
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.common)
    implementation(libs.androidx.appcompat)
    implementation(libs.firebase.database)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

// Aplicar el plugin de Google Services
apply(plugin = "com.google.gms.google-services")
