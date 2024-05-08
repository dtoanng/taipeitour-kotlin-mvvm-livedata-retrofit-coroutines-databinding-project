object AppDependencies {
    // compose
    const val COMPOSE_UI = "androidx.compose.ui:ui:${BuildDependencyVersions.COMPOSE_VERSION}"
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${BuildDependencyVersions.COMPOSE_VERSION}"
    const val COMPOSE_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${BuildDependencyVersions.COMPOSE_VERSION}"
    const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation:${BuildDependencyVersions.COMPOSE_VERSION}"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${BuildDependencyVersions.COMPOSE_VERSION}"
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:${BuildDependencyVersions.COMPOSE_ACTIVITY_VERSION}"

    //SQLDelight
    const val SQLDELIGHT_COMMON = "com.squareup.sqldelight:runtime:${BuildDependencyVersions.SQLDELIGHT_VERSION}"
    const val SQLDELIGHT_ANDROID = "com.squareup.sqldelight:android-driver:${BuildDependencyVersions.SQLDELIGHT_VERSION}"
    const val SQLDELIGHT_IOS = "com.squareup.sqldelight:native-driver:${BuildDependencyVersions.SQLDELIGHT_VERSION}"

    //Coroutines
    const val SQLDELIGHT_COROUTINES = "com.squareup.sqldelight:coroutines-extensions:${BuildDependencyVersions.SQLDELIGHT_VERSION}"
    const val KOTLINX_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependencyVersions.COROUTINES_VERSION}"

    // android
    const val ANDROID_APPCOMPAT = "androidx.appcompat:appcompat:${BuildDependencyVersions.ANDROID_APPCOMPAT}"
    const val ANDROID_CORE = "androidx.core:core-ktx:${BuildDependencyVersions.ANDROID_CORE_KTX}"
    const val ANDROID_MATERIAL = "com.google.android.material:material:${BuildDependencyVersions.ANDROID_MATERIAL}"
    const val ANDROID_RECYCLE_VIEW = "androidx.recyclerview:recyclerview:${BuildDependencyVersions.ANDROID_RECYCLE_VIEW}"
    const val ANDROID_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${BuildDependencyVersions.ANDROID_CONSTRAINT_LAYOUT}"
    const val ANDROID_DATASTORE = "androidx.datastore:datastore-preferences:${BuildDependencyVersions.ANDROID_DATASTORE}"
    const val ANDROID_GLIDE = "com.github.bumptech.glide:glide:${BuildDependencyVersions.GLIDE}"
    const val ANDROID_DATA_BINDING = "androidx.databinding:databinding-compiler-common:${BuildDependencyVersions.ANDROID_DATA_BINDING}"

    const val GOOGLE_GSON = "com.google.code.gson:gson:${BuildDependencyVersions.GOOGLE_GSON}"
    const val RXJAVA = "io.reactivex.rxjava2:rxjava:${BuildDependencyVersions.RX_JAVA}"
    const val RX_ANDROID = "io.reactivex.rxjava2:rxandroid:${BuildDependencyVersions.ANDROID_RX_ANDROID}"

    // square
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildDependencyVersions.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${BuildDependencyVersions.RETROFIT_CONVERTER_GSON}"
    const val OKHTTP3 = "com.squareup.okhttp3:okhttp:${BuildDependencyVersions.OKHTTP3}"

    const val JUNIT = "junit:junit:${BuildDependencyVersions.JUNIT}"
    const val EXT_JUNIT = "androidx.test.ext:junit:${BuildDependencyVersions.EXT_JUNIT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${BuildDependencyVersions.ESPRESSO}"
    const val ROUNDED_IMAGEVIEW = "com.makeramen:roundedimageview:${BuildDependencyVersions.ROUNDED_IMAGEVIEW}"
}