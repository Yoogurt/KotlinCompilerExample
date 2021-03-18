import plugin.myPlugin

plugins {
    id(PluginName.androidApplication)
    id(PluginName.kotlinAndroid)
    id("Utils")
}

android {
    compileSdkVersion = AndroidBuildConfig.compileSdkVersion
    buildToolsVersion = AndroidBuildConfig.buildToolsVersion

    defaultConfig {
        applicationId = "com.example.gradleplugintestapplication"
        setMinSdkVersion(AndroidBuildConfig.minSdkVersion)
        setTargetSdkVersion(AndroidBuildConfig.targetSdkVersion)

        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        maybeCreate("release").apply {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        incremental = false
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

myPlugin {
    enable = false
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to arrayOf("*.jar")))
    implementation(kotlin("stdlib", Versions.kotlin))
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    "kotlinPlugin"(project(":plugin_module"))
}