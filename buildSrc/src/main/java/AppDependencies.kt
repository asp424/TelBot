import org.gradle.kotlin.dsl.DependencyHandlerScope

const val composeVersion = "1.2.0-alpha06"

fun DependencyHandlerScope.impl() {
    //Compose
    with(composeVersion) {
        listOf(
            "androidx.compose.ui:ui:$this",
            "androidx.compose.ui:ui-tooling:$this",
            "androidx.compose.compiler:compiler:$this",
            "androidx.compose.foundation:foundation:$this",
            "androidx.compose.material:material:$this",
            "androidx.compose.material:material-icons-core:$this",
            "androidx.compose.material:material-icons-extended:$this",
            "androidx.compose.animation:animation:$this",
            "androidx.compose.material3:material3:1.0.0-alpha08",
            "com.google.accompanist:accompanist-pager-indicators:0.24.1-alpha",
            "com.google.accompanist:accompanist-pager:0.24.1-alpha",
            "com.google.accompanist:accompanist-navigation-animation:0.24.1-alpha"
        ).forEach { addD(dep = it) }
    }
    addD(dep = "androidx.activity:activity-compose:1.4.0")
    addD(dep = "com.squareup.leakcanary:leakcanary-android:2.8.1")

    //Base
    addD(dep = "androidx.core:core-ktx:1.8.0-alpha06")
    addD(dep = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

    //Dagger-2
    addD(dep = "com.google.dagger:dagger:2.41")
    addD(dep = "com.google.dagger:dagger-compiler:2.41", method = kapt)

    //Retrofit
    addD(dep = "com.squareup.retrofit2:retrofit:2.9.0")
    addD(dep = "com.squareup.retrofit2:converter-gson:2.9.0")

    //TelBot
    addD(dep = "io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.0.6")

//Lifecycle
    with("2.5.0-alpha05") {
        listOf(
            "androidx.lifecycle:lifecycle-runtime-ktx:$this",
            "androidx.lifecycle:lifecycle-viewmodel-ktx:$this"
        ).forEach { addD(dep = it) }
    }
}

val listPlugins = listOf(
    "com.android.application",
    "org.jetbrains.kotlin.android",
    "kotlin-kapt"
)

private fun DependencyHandlerScope.addD(method: String = imp, dep: Any) = add(method, dep)
private val imp get() = "implementation"
private val kapt get() = "kapt"




