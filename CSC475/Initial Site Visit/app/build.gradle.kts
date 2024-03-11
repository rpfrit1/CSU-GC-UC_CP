plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt") version "1.9.22"
}

android {
    namespace = "edu.csuglobal.csc475.initialsitevisit"
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.csuglobal.csc475.initialsitevisit"
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
}
val roomversion = "2.6.1"
val espressoversion = "3.4.0"
val junitversion = "4.13.2"
val androidx_test_version = "1.4.0"
val androidx_test_ext_version = "1.1.5"
val androidx_test_rules_version = "1.4.0"
val androidx_test_runner_version = "1.4.0"
val androidx_test_core_version = "1.4.0"
val androidx_test_uiautomator_version = "2.2.0"
val truth_version = "1.1.3"
val testJunitVersion = "4.13.2"
val testRulesVersion = "1.4.0"
val testRunnerVersion = "1.4.0"
val androidXTestVersion = "1.4.0"
val truthVersion = "1.1.3"
val espressoVersion = "3.4.0"
val junitVersion = "4.13.2"
val androidxTestVersion = "1.4.0"
val androidxTestExtVersion = "1.1.3"
val androidxTestRunnerVersion = "1.4.0"
val androidxTestRulesVersion = "1.4.0"
val androidxTestCoreVersion = "1.4.0"
val androidxTestUiautomatorVersion = "2.2.0"

dependencies {
    implementation(libs.core.ktx)
    implementation("androidx.room:room-runtime:$roomversion")
    annotationProcessor("androidx.room:room-compiler:$roomversion")
    kapt("androidx.room:room-compiler:$roomversion")
    implementation("androidx.room:room-ktx:$roomversion")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    // Core library
    androidTestImplementation("androidx.test:core:1.5.0")

    // AndroidJUnitRunner and JUnit Rules
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")

    // Assertions
    androidTestImplementation("androidx.test.ext:junit:$androidx_test_ext_version")
    androidTestImplementation("androidx.test.ext:truth:1.5.0")

    // Espresso dependencies
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-accessibility:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-web:3.5.1")
    androidTestImplementation("androidx.test.espresso.idling:idling-concurrent:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-idling-resource:3.5.1")
}