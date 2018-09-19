package activity.nivedita.com.di.builder;

import activity.nivedita.com.di.module.MainActivityModule;
import activity.nivedita.com.di.module.MovieFragmentProvider;
import activity.nivedita.com.di.module.TvShowsModuleProvider;
import activity.nivedita.com.helloretrofit.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * The Activity Builder class to defined for a specific activity
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,
            MovieFragmentProvider.class,
            TvShowsModuleProvider.class
    })
    abstract MainActivity bindMainActivity();
}
