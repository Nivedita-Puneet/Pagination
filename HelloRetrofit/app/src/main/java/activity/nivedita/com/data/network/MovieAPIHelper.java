package activity.nivedita.com.data.network;

import javax.inject.Inject;
import javax.inject.Singleton;

import activity.nivedita.com.model.TopRatedMovies;
import activity.nivedita.com.networkutils.ConstantsUtil;
import activity.nivedita.com.networkutils.MovieService;
import io.reactivex.Flowable;

/**
 * API Helper class used to implement the interfaces from helper classes.
 */
@Singleton
public class MovieAPIHelper implements APIHelper {

    private final MovieService movieService;

    @Inject
    public MovieAPIHelper(MovieService movieService) {
        this.movieService = movieService;
    }


    @Override
    public Flowable<TopRatedMovies> getTopRatedMovies() {
        return movieService.getTopRatedMovies(ConstantsUtil.TMDB_API_KEY,
                "en_US",
                ConstantsUtil.TOTAL_PAGES);
    }
}
