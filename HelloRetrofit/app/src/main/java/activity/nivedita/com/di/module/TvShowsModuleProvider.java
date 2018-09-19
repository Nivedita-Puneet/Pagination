package activity.nivedita.com.di.module;

import activity.nivedita.com.helloretrofit.TVShowsFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * The module provider to be defined for the TVShows.
 */

@Module
public abstract class TvShowsModuleProvider {

    @ContributesAndroidInjector(modules = TvShowsModule.class)
    abstract TVShowsFragment provideTVShowsFragmentFactory();
}
