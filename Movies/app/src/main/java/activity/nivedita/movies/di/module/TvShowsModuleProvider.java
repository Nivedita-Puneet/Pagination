package activity.nivedita.movies.di.module;

import activity.nivedita.movies.movies.TVShowsFragment;
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
