plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    //id("dagger.hilt.android.plugin") // Explicitly add Hilt plugin
    id("dagger.hilt.android.plugin") // Correct Hilt plugin
    id("kotlin-kapt") // Correct way to use Kapt

}

android {
    namespace = "com.example.moviesapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.moviesapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding= true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)





    implementation (libs.circleimageview)
    implementation (libs.androidx.legacy.support.v4)
    implementation (libs.androidx.room.runtime)

    // implementation 'com.google.android.material:material:1.1.0-alpha08

    implementation (libs.okhttp3.logging.interceptor)
    implementation (libs.okhttp)
    implementation (libs.okhttp3.logging.interceptor)
    implementation (libs.converter.scalars)
    implementation (libs.converter.gson)
    implementation (libs.rxjava)


    // LiveData
    implementation (libs.androidx.lifecycle.livedata.ktx)

    // viewModelScope
    implementation (libs.androidx.lifecycle.viewmodel.ktx)

    //LifecycleScope
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")


    //kotlin co-routines dependencies
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    implementation (libs.adapter.rxjava2)
    implementation (libs.glide)


    // Hilt Dependency Injection
    implementation("com.google.dagger:hilt-android:2.46.1")
    kapt("com.google.dagger:hilt-compiler:2.46.1")

    androidTestImplementation("com.google.dagger:hilt-android-testing:2.46.1")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.46.1")

    testImplementation("com.google.dagger:hilt-android-testing:2.46.1")
    kaptTest("com.google.dagger:hilt-compiler:2.46.1")

}
kapt {
    correctErrorTypes = true
}