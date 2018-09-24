package activity.nivedita.movies.di.component;

import android.app.Application;

import javax.inject.Singleton;

import activity.nivedita.movies.base.HelloRetrofitApplication;
import activity.nivedita.movies.di.builder.ActivityBuilder;
import activity.nivedita.movies.di.module.ApplicationModule;
import activity.nivedita.movies.di.module.NetworkModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * The Main application component to be defined through out.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        ApplicationModule.class, ActivityBuilder.class,NetworkModule.class})
public interface ApplicationComponent {

    void inject(HelloRetrofitApplication application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }


}