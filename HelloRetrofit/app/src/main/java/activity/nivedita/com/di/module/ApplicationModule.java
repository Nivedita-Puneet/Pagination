package activity.nivedita.com.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import activity.nivedita.com.data.AppDataManager;
import activity.nivedita.com.data.DataManager;
import activity.nivedita.com.data.network.APIHelper;
import activity.nivedita.com.data.network.MovieAPIHelper;
import activity.nivedita.com.di.scope.ApplicationContext;
import activity.nivedita.com.networkutils.MovieService;
import activity.nivedita.com.networkutils.paginate.Paginateutil;
import activity.nivedita.com.networkutils.rx.AppSchedulerprovider;
import activity.nivedita.com.networkutils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import io.reactivex.processors.PublishProcessor;

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
