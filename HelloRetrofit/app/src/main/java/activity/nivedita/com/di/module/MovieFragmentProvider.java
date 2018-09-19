package activity.nivedita.com.di.module;

import activity.nivedita.com.helloretrofit.MoviesFragment;
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
