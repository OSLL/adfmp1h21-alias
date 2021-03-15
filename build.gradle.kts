//val kotlinVersion: String = "1.4.30"

buildscript {
//    ext.kotlin_version = "1.4.30"
    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath(kotlin("gradle-plugin", version = "1.4.30"))
        classpath(kotlin("gradle-plugin", version = "1.4.30"))
        classpath(kotlin("serialization", version = "1.4.30"))
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}