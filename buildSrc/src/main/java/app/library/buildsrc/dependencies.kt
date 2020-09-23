package app.library.buildsrc

object Libs {
    const val timber = "com.jakewharton.timber:timber:4.7.1"

    object Google {
        const val material = "com.google.android.material:material:1.3.0-alpha02"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.0-alpha01"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.2.0-alpha05"
        const val preference = "androidx.preference:preference-ktx:1.1.1"

        object Navigation {
            private const val version = "2.3.0"
            const val fragment = "androidx.navigation:navigation-fragment:$version"
            const val ui = "androidx.navigation:navigation-ui:$version"
            const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Fragment {
            private const val version = "1.3.0-alpha07"
            const val fragment = "androidx.fragment:fragment:$version"
        }

        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.1"

        object Lifecycle {
            private const val version = "2.3.0-alpha06"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"
            const val common = "androidx.lifecycle:lifecycle-common-java8:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime:$version"
        }
    }

    object RxJava {
        const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.19"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
        const val rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:$version"
    }

    object OkHttp {
        private const val version = "4.8.1"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Epoxy {
        private const val version = "3.11.0"
        const val epoxy = "com.airbnb.android:epoxy:$version"
        const val processor = "com.airbnb.android:epoxy-processor:$version"
    }

    object Stetho {
        private const val version = "1.5.1"
        const val stetho = "com.facebook.stetho:stetho:$version"
        const val okhttp3 = "com.facebook.stetho:stetho-okhttp3:$version"
    }

    object Glide {
        private const val version = "4.11.0"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
    }

    object Hilt {
        private const val version = "2.28.3-alpha"
        const val library = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }
}
