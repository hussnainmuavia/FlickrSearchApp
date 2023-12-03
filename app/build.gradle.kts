plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
}

android {
    namespace = "com.example.flickersearchapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.flickersearchapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        multiDexEnabled = true
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
    kotlinOptions {
        jvmTarget = "17"
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
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.1.2")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2") // Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2") // Consume stream of data with ViewModels
    implementation("androidx.compose.runtime:runtime-livedata") //  Integration with LiveData
    implementation("androidx.navigation:navigation-compose:2.7.5") //  Integration with navigation
    implementation("io.coil-kt:coil-compose:2.5.0") // Load an image from the internet

    // Retrofit section for network calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    //Pagination
    implementation("androidx.paging:paging-compose:3.3.0-alpha02")

    //Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")

    //Testing Dependencies
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:2.25.0")
    testImplementation("io.mockk:mockk:1.10.5")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.6.21")

    testImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("androidx.test:runner:1.5.2")


    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    // optional - Jetpack Compose integration
    implementation("androidx.paging:paging-compose:3.3.0-alpha02")
    // alternatively - without Android dependencies for tests
    testImplementation("androidx.paging:paging-common-ktx:3.2.1")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

hilt {
    enableTransformForLocalTests = true
}