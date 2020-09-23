package app.library.inject;

import javax.inject.Singleton;

import app.library.util.AppSchedulers;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {

    @Singleton
    @Provides
    public AppSchedulers provideSchedulers() {
        return new AppSchedulers(
                Schedulers.io(),
                AndroidSchedulers.mainThread()
        );
    }
}
