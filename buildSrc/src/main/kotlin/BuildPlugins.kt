object BuildPlugins {
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val MULTIPLATFORM = "multiplatform"
    const val DAGGER_HILT = "com.google.dagger.hilt.android"

    // SQLDelight
    const val SQLDELIGHT_GRADLE_PLUGIN = "com.squareup.sqldelight:gradle-plugin:${BuildDependencyVersions.SQLDELIGHT_VERSION}"
    const val SQLDELIGHT_PLUGIN = "com.squareup.sqldelight"
}