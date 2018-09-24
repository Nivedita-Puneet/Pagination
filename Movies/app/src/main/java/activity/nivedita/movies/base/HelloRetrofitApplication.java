package activity.nivedita.movies.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


import javax.inject.Inject;

import activity.nivedita.movies.di.component.ApplicationComponent;
import activity.nivedita.movies.di.component.DaggerApplicationComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by NEETU on 06-03-2018.
 */

public class HelloRetrofitApplication extends Application implements HasActivityInjector {

    ApplicationComponent applicationComponent;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationComponent.builder().application(this).build().inject(HelloRetrofitApplication.this);
    }

    public static HelloRetrofitApplication get(Context context) {

        return (HelloRetrofitApplication) context.getApplicationContext();
    }

   /* public ApplicationComponent getComponent() {

        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .networkModule(new NetworkModule()).build();
        }

        return applicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }*/

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
