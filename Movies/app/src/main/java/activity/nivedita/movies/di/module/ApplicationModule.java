package activity.nivedita.movies.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import activity.nivedita.movies.data.AppDataManager;
import activity.nivedita.movies.data.DataManager;
import activity.nivedita.movies.data.network.APIHelper;
import activity.nivedita.movies.data.network.MovieAPIHelper;
import activity.nivedita.movies.di.scope.ApplicationContext;
import activity.nivedita.movies.networkutils.rx.AppSchedulerprovider;
import activity.nivedita.movies.networkutils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

/**
 * Created by NEETU on 08-03-2018.
 */

@Module
public class ApplicationModule {


    @Provides
    @ApplicationContext
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager getDataManager(AppDataManager dataManager) {
        return dataManager;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerprovider();
    }


    @Provides
    @Singleton
    APIHelper getSunShineAPIHelper(MovieAPIHelper movieAPIHelper) {
        return movieAPIHelper;
    }

}
