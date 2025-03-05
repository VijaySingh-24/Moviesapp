👇 Structures Used
MVVM

Dependency Injection | Hilt

Coroutine

Retrofit

View Binding 

Parcelable

Glide


📱 Screens
MoviesList Screen

Search Screen

Detail Screen

Watchlist Screen

Favorites Screen

✏️ Dependency
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
app build.gradle:

plugins {

    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("dagger.hilt.android.plugin") 
    id("kotlin-kapt") 

}

buildFeatures {
      viewBinding true
      
 }
project build.gradle:

plugins {

      alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.46.1" apply false

❗ Manifest File

<uses-permission android:name="android.permission.INTERNET" />

👇 API
(https://dummyjson.com/)
