package activity.nivedita.movies.data.network;

import activity.nivedita.movies.model.TopRatedMovies;
import activity.nivedita.movies.model.tvshows.TvShows;
import io.reactivex.Flowable;

/**
 * Interface defined
 */

public interface APIHelper {

    Flowable<TopRatedMovies> getTopRatedMovies(int currentPage);

    Flowable<TvShows>  getPopularTVShows(int currentPage);
}
