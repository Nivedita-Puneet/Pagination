package activity.nivedita.com.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import activity.nivedita.com.data.AppDataManager;
import activity.nivedita.com.data.DataManager;
import activity.nivedita.com.data.network.APIHelper;
import activity.nivedita.com.data.network.MovieAPIHelper;
import activity.nivedita.com.di.scope.ApplicationContext;
import activity.nivedita.com.networkutils.paginate.Paginateutil;
import dagger.Module;
import dagger.Provides;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by NEETU on 08-03-2018.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }


    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager getDataManager(AppDataManager dataManager) {
        return dataManager;
    }

    @Provides
    @Singleton
    APIHelper getSunShineAPIHelper(MovieAPIHelper movieAPIHelper) {
        return movieAPIHelper;
    }

}
