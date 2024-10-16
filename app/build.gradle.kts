plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.marcelos.blocodenotas"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.marcelos.blocodenotas"
        minSdk = 24
        targetSdk = 34
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
    android.applicationVariants.configureEach {
        val variantName = name

        kotlin.sourceSets {
            getByName(variantName) {
                kotlin.srcDir("build/generated/ksp/$variantName/kotlin")
            }
        }
    }

    val javaVersion = libs.versions.java.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(javaVersion)
        targetCompatibility = JavaVersion.toVersion(javaVersion)
    }
    composeCompiler {
        enableStrongSkippingMode = true
    }
    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += setOf(
                "/META-INF/{AL2.0,LGPL2.1,LICENSE.md,LICENSE.txt,LICENSE-notice.md,LICENSE-notice.txt}"
            )
        }
    }

    testOptions {
        unitTests.all {
            it.allJvmArgs = (it.allJvmArgs + "-Dnet.bytebuddy.experimental=true").toList()
        }
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
    implementation(libs.androidx.constraintlayout)
    implementation(libs.google.accompanist.system.ui.controller)
    implementation(libs.datastore)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    ksp(libs.koin.ksp.compiler)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.coroutines)

    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.mockk.io)
    testImplementation(libs.bundles.mockito.test)
    androidTestImplementation(libs.bundles.koin.test)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.mockk.io)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
