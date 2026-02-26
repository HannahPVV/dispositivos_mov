plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "hannah.valencia.clima"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "hannah.valencia.clima"
        minSdk = 24
        targetSdk = 36
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
    buildFeatures{
        viewBinding= true //Esta línea la agregamos
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

    //Libreria principal de Media3 para ExoPlayer
    implementation("androidx.media3:media3-exoplayer:1.5.1")
    // UI components (PlayerView para XML)
    implementation("androidx.media3:media3-ui:1.5.1")
    // Soporte para formatos comunes (Dash, HLS, etc.)
    implementation("androidx.media3:media3-common:1.5.1")
    //aqui pegar lo de lottie hacer syns *seguir xml y kotlin y iu tradiconal
    //copair el xml en saplas y lo pegamos y tienes su id, el src tien que ir dentro de ssets si es json ,
    //o la oa froma porfgrma es ir al acticity poner funcion y se conceta con binding

    implementation("com.airbnb.android:lottie:6.4.1")

    //Biometrics
    implementation("androidx.media3:media3-common:1.5.1")

    implementation("androidx.biometric:biometric:1.2.0-alpha05")

}
