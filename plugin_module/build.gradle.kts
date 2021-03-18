plugins {
    java
    kotlin("jvm")
    id("kotlin-kapt")
}

repositories {
    mavenCentral()
}

tasks.findByName("build")?.apply {
    println("-----------------------------tasking $project")
    val jarTask = tasks.findByName("jar")
    println("execute task jar first $jarTask")
    dependsOn(jarTask)
}

tasks.findByName("jar")?.apply {
    doLast {
        println("jar task finish")
    }
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly(kotlin("compiler"))
    implementation("com.google.auto.service:auto-service:1.0-rc7")
    "kapt"("com.google.auto.service:auto-service:1.0-rc7")
}
