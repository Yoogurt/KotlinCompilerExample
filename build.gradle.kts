// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.Plugin.androidBuildTools}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java){
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf("")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}