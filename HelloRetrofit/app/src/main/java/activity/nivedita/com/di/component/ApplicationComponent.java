package activity.nivedita.com.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import activity.nivedita.com.data.DataManager;
import activity.nivedita.com.di.module.ApplicationModule;
import activity.nivedita.com.di.module.NetworkModule;
import activity.nivedita.com.di.scope.ApplicationContext;
import dagger.Component;

/**
 * Created by NEETU on 08-03-2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    Application application();

    @ApplicationContext
    Context context();

    DataManager dataManager();
}