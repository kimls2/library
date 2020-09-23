package app.library;

import android.app.Application;

import com.facebook.stetho.Stetho;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class LibraryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}
