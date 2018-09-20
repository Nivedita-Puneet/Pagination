package activity.nivedita.com.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import activity.nivedita.com.base.HelloRetrofitApplication;
import activity.nivedita.com.data.DataManager;
import activity.nivedita.com.di.module.ApplicationModule;
import activity.nivedita.com.di.module.NetworkModule;
import activity.nivedita.com.di.scope.ApplicationContext;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * The Main application component to be defined through out.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(HelloRetrofitApplication application);

    @ApplicationContext
    Context context();

    DataManager dataManager();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}