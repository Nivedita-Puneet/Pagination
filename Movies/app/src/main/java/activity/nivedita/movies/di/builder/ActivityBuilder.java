package activity.nivedita.movies.di.builder;

import activity.nivedita.movies.di.module.MainActivityModule;
import activity.nivedita.movies.di.module.MovieFragmentProvider;
import activity.nivedita.movies.di.module.TvShowsModuleProvider;
import activity.nivedita.movies.movies.MainActivity;
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
