package activity.nivedita.com.di.module;

import android.app.Application;
import android.content.Context;

import activity.nivedita.com.di.scope.ApplicationContext;
import dagger.Module;
import dagger.Provides;

/**
 * Created by NEETU on 08-03-2018.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;
    boolean isRequestOnWay = false;

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
    boolean requestOnWay() {
        return isRequestOnWay;
    }
}
