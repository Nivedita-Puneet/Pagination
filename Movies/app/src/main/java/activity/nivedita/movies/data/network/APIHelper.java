package activity.nivedita.movies.data.network;

import activity.nivedita.movies.model.TopRatedMovies;
import io.reactivex.Flowable;

/**
 * Interface defined
 */

public interface APIHelper {

    Flowable<TopRatedMovies> getTopRatedMovies(int currentPage);

}
