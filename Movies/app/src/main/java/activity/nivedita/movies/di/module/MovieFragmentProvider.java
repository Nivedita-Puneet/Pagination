package activity.nivedita.movies.di.module;

import activity.nivedita.movies.movies.MoviesFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Define a provider class for a Movies Fragment Module.
 */

@Module
public abstract class MovieFragmentProvider {

    @ContributesAndroidInjector(modules = MoviesFragmentModule.class)
    abstract MoviesFragment provideMovieFragmentFactory();
}
