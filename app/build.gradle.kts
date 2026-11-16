plugins { id("com.android.application"); id("org.jetbrains.kotlin.android") }
android {
    namespace = "com.modestov.worldcinema"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.modestov.worldcinema"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.1" }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_1_8; targetCompatibility = JavaVersion.VERSION_1_8 }
    kotlinOptions { jvmTarget = "1.8" }
}
dependencies {
    implementation("androidx.wear.compose:compose-material:1.3.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("com.squareup.picasso:picasso:2.8")
}
