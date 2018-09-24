package activity.nivedita.movies.di.module;

import javax.inject.Singleton;

import activity.nivedita.movies.networkutils.MovieService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by PUNEETU on 08-03-2018.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    MovieService provideMovieAPI() {

        return MovieService.Creator.newMovieAPI();
    }
}
