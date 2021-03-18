repositories {
    mavenLocal()
    mavenCentral()
    google()
    jcenter()
}

plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        maybeCreate("Utils").apply {
            id = "Utils"
            implementationClass = "plugin.UtilsPlugin"
        }
    }
}

dependencies {
    api("com.android.tools.build:gradle:3.6.4")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30")
}