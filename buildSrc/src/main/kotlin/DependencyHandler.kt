import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
    add("implementation", dependencyNotation)


fun DependencyHandler.api(dependencyNotation: String): Dependency? =
    add("api", dependencyNotation)


fun DependencyHandler.kapt(dependencyNotation: String): Dependency? =
    add("kapt", dependencyNotation)


fun DependencyHandler.debugImplementation(dependencyNotation: String): Dependency? =
    add("debugImplementation", dependencyNotation)


fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? =
    add("testImplementation", dependencyNotation)


fun DependencyHandler.androidTestImplementation(dependencyNotation: String): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.addDependenciesForCompose() {
    implementation(AppDependencies.COMPOSE_UI)
    implementation(AppDependencies.COMPOSE_MATERIAL)
    implementation(AppDependencies.COMPOSE_UI_TOOLING)
    implementation(AppDependencies.COMPOSE_UI_TOOLING_PREVIEW)
    implementation(AppDependencies.COMPOSE_FOUNDATION)
    implementation(AppDependencies.COMPOSE_ACTIVITY)
}

fun DependencyHandler.addDependencies() {
    implementation(AppDependencies.ANDROID_APPCOMPAT)
    implementation(AppDependencies.ANDROID_CORE)
    implementation(AppDependencies.ANDROID_MATERIAL)
    implementation(AppDependencies.ANDROID_RECYCLE_VIEW)
    implementation(AppDependencies.ANDROID_CONSTRAINT_LAYOUT)
    implementation(AppDependencies.ANDROID_DATASTORE)
    implementation(AppDependencies.ANDROID_DATA_BINDING)
    implementation(AppDependencies.KOTLINX_COROUTINES)
    implementation(AppDependencies.SQLDELIGHT_COROUTINES)
    implementation(AppDependencies.GOOGLE_GSON)
    implementation(AppDependencies.ANDROID_GLIDE)
    implementation(AppDependencies.RETROFIT)
    implementation(AppDependencies.RETROFIT_CONVERTER_GSON)
    implementation(AppDependencies.OKHTTP3)
    implementation(AppDependencies.RXJAVA)
    implementation(AppDependencies.RX_ANDROID)
    implementation(AppDependencies.ROUNDED_IMAGEVIEW)
    implementation(AppDependencies.DAGGER_HILT_ANDROID)
    kapt(AppDependencies.HILT_COMPILER)
    testImplementation(AppDependencies.JUNIT)
    androidTestImplementation(AppDependencies.EXT_JUNIT)
    androidTestImplementation(AppDependencies.ESPRESSO)
}

