// Top-level build file where you can add configuration options common to all sub-projects/modules.
//buildscript {
   // val kotlin_version = "1.6.21"
//    val nav_version = "2.5.0"
//
//
//
//    repositories {
//        google()
//        mavenCentral()
//    }
//    dependencies {
//        classpath ("com.android.tools.build:gradle:7.4.2")
//        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
//        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
//
//        // NOTE: Do not place your application dependencies here; they belong
//        // in the individual module build.gradle files
//    }
//}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}