plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.shilowski.gamesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.shilowski.gamesapp"
        minSdk = 26
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    packaging {
        resources.excludes.add("META-INF/*")
    }
}

// todo: refactor dependencies
dependencies {
    val ktorServer = "2.3.6"
    val coroutines = "1.7.1"
    val koin = "3.4.0"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.appcompat:appcompat:1.6.1")

    // navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")

    // koin
    implementation("io.insert-koin:koin-core:$koin")
    implementation("io.insert-koin:koin-android:$koin")

    // server
    implementation("io.ktor:ktor-server-core:$ktorServer")
    implementation("io.ktor:ktor-server-websockets:$ktorServer")
    implementation("io.ktor:ktor-server-sessions:$ktorServer")
    implementation("io.ktor:ktor-server-netty:$ktorServer")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorServer")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorServer")

    // client
    implementation("io.ktor:ktor-client-core:$ktorServer")
    implementation("io.ktor:ktor-client-cio:$ktorServer")
    implementation("io.ktor:ktor-client-websockets:$ktorServer")
    implementation("io.ktor:ktor-client-logging:$ktorServer")
    implementation("io.ktor:ktor-client-android:$ktorServer")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorServer")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    testImplementation("junit:junit:4.13.2")
    testImplementation("io.insert-koin:koin-test:$koin")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
}