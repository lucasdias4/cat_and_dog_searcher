package com.lucasdias.buildSrc

/**
 * Article with similar implementation: https://proandroiddev.com/gradle-dependency-management-with-kotlin-94eed4df9a28
 */
object Dependencies {

    object Core {
        const val appcompat: String = "androidx.appcompat:appcompat:" + Versions.app_compat

        const val core_ktx: String = "androidx.core:core-ktx:" + Versions.core_ktx

        const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:" + Versions.coroutines

        const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:" + Versions.coroutines

        const val legacy_support: String =
            "androidx.legacy:legacy-support-v4:" + Versions.legacy_support

        const val navigation_fragment: String = "androidx.navigation:navigation-fragment-ktx:" + Versions.navigation

        const val navigation_ui: String = "androidx.navigation:navigation-ui-ktx:" + Versions.navigation

        const val kotlin_stdlib: String = "org.jetbrains.kotlin:kotlin-stdlib:" + Versions.kotlin

        const val kotlin_stdlib_jdk7: String =
            "org.jetbrains.kotlin:kotlin-stdlib-jdk7:" + Versions.kotlin

        const val kotlin_stdlib_jdk8: String =
            "org.jetbrains.kotlin:kotlin-stdlib-jdk8:" + Versions.kotlin

        const val rx_java: String = "io.reactivex.rxjava3:rxjava:" + Versions.rx_java

        const val rx_android: String = "io.reactivex.rxjava3:rxandroid:" + Versions.rx_android
    }

    object Data {

        const val preference: String =
            "androidx.preference:preference-ktx:" + Versions.preference

        const val logging_interceptor: String = "com.squareup.okhttp3:logging-interceptor:" + Versions.logging_interceptor

        /**
         * https://github.com/square/retrofit/
         */
        const val retrofit: String = "com.squareup.retrofit2:retrofit:" + Versions.retrofit

        const val retrofit_converter_gson: String = "com.squareup.retrofit2:converter-gson:" + Versions.retrofit

        const val retrofit_adapter_rx_java: String = "com.squareup.retrofit2:adapter-rxjava3:" + Versions.retrofit
    }

    object DI {
        /**
         * https://github.com/InsertKoinIO/koin
         */
        const val koin: String = "org.koin:koin-android:" + Versions.koin

        const val koin_core: String = "org.koin:koin-core:" + Versions.koin

        const val koin_view_model: String = "org.koin:koin-androidx-viewmodel:" + Versions.koin
    }

    object Module {
        const val app = ":app"

        const val base = ":base"

        const val core = ":core"

        const val extensions = ":extensions"

        const val data = ":data"

        const val domain = ":domain"

        const val feature_animal = ":feature-animal"

        const val resource = ":resource"

        const val ui_component = ":ui-component"
    }

    object Tool {
        const val gradle: String = "com.android.tools.build:gradle:" + Versions.gradle

        const val gradle_kotlin_plugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:" + Versions.kotlin

        /**
         * https://github.com/pinterest/ktlint
         */
        const val ktlint: String = "com.pinterest:ktlint:" + Versions.ktlint

        const val navigation_safe_args: String = "androidx.navigation:navigation-safe-args-gradle-plugin:" + Versions.navigation
    }

    object Test {
        const val core_testing: String = "androidx.arch.core:core-testing:" + Versions.core_testing

        /**
         * https://developer.android.com/testing
         */
        const val espresso_contrib: String = "androidx.test.espresso:espresso-contrib:" +
                Versions.espresso

        const val espresso_core: String = "androidx.test.espresso:espresso-core:" +
                Versions.espresso

        const val espresso_intents: String = "androidx.test.espresso:espresso-intents:" +
                Versions.espresso

        const val fragment_testing: String =
            "androidx.fragment:fragment-testing:" + Versions.fragment_testing

        /**
         * http://junit.org
         */
        const val junit: String = "junit:junit:" + Versions.junit

        const val junit_android: String = "androidx.test.ext:junit:" + Versions.junit_android

        const val koin: String = "org.koin:koin-test:" + Versions.koin

        /**
         * https://github.com/Kotlin/kotlinx.coroutines
         */
        const val kotlinx_coroutines_test: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:" +
                    Versions.coroutines

        /**
         * http://mockk.io
         */
        const val mockk: String = "io.mockk:mockk:" + Versions.mockk

        const val mockk_android: String = "io.mockk:mockk-android:" + Versions.mockk

        const val test_runner: String = "androidx.test:runner:" + Versions.runner
    }

    object UI {

        const val card_view: String = "androidx.cardview:cardview:" + Versions.card_view

        const val constraint_layout: String = "androidx.constraintlayout:constraintlayout:" +
                Versions.constraint_layout

        /**
         * https://github.com/bumptech/glide
         */
        const val glide: String = "com.github.bumptech.glide:glide:" + Versions.glide

        const val glide_annotations: String =
            "com.github.bumptech.glide:annotations:" + Versions.glide

        const val glide_compiler: String = "com.github.bumptech.glide:compiler:" + Versions.glide

        const val material: String = "com.google.android.material:material:" + Versions.material

        /**
         * AndroidX Components and Tools
         * https://developer.android.com/jetpack/androidx
         */

        const val recycler_view: String =
            "androidx.recyclerview:recyclerview:" + Versions.recycler_view

        /**
         * http://facebook.github.io/shimmer-android
         */
        const val shimmer: String = "com.facebook.shimmer:shimmer:" + Versions.shimmer
    }
}
