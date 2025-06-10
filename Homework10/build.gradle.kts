// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.devtools.ksp) apply false

    alias(libs.plugins.secret.gradle.plugin) apply false

    alias(libs.plugins.dagger.hilt.plugin) apply false
    alias(libs.plugins.android.library) apply false

    //Firebase
    alias(libs.plugins.gms.google.services) apply false
    //Firebase Crashlytics
    alias(libs.plugins.google.firebase.crashlytics) apply false
}

private val versionMajor = 1
private val versionMinor = 0

val versionName by extra(initialValue = "$versionMajor.$versionMinor")
val versionCode by extra(initialValue = versionMajor * 1000 + versionMinor * 10)