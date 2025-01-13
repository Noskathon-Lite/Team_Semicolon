plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.surakhsit_nepal"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.surakhsit_nepal"
        minSdk = 28
        targetSdk = 35
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //visibility ko lagi
    implementation(libs.androidx.material.icons.extended)

    //navigation ko lagi dependency
    implementation(libs.androidx.navigation.compose)

    // grid layout dependecy
    implementation(libs.androidx.foundation)

    //viewModel ko lagi
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //retrofit api ko lagi
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //image dekhauna laii coil dependency
    implementation(libs.coil.compose)

    //okhttp dependency
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(platform(libs.okhttp.bom))

    //location ko lagi
    implementation(libs.play.services.location)
    implementation(libs.play.services.maps)

    //permission ko lagi
    implementation(libs.accompanist.permissions)

    // phone number ko lagi
    implementation(libs.play.services.auth)

    //datastore
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.preferences.v100)

    // RxJava2 extensions for DataStore
    implementation(libs.androidx.datastore.rxjava2)

    //map ko lagi
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location) //live location ko lagi


    implementation(libs.osmdroid.osmdroid.android)
    implementation(libs.ui)

    //coroutinesx ko lagi
    implementation(libs.kotlinx.coroutines.play.services)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.android.maps.utils)
//    implementation(libs.osmdroid.geopackage)
//    implementation(libs.osmdroid.third.party)




}