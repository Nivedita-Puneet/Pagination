package activity.nivedita.com.base;

import android.app.Application;
import android.content.Context;


import activity.nivedita.com.di.component.ApplicationComponent;
import activity.nivedita.com.di.component.DaggerApplicationComponent;
import activity.nivedita.com.di.module.ApplicationModule;
import activity.nivedita.com.di.module.NetworkModule;

/**
 * Created by NEETU on 06-03-2018.
 */

public class HelloRetrofitApplication extends Application {

    ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {

        super.onCreate();


    }

    public static HelloRetrofitApplication get(Context context) {

        return (HelloRetrofitApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {

        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .networkModule(new NetworkModule()).build();
        }

        return applicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
