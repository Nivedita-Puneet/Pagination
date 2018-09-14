package activity.nivedita.com.data.network;

import activity.nivedita.com.model.TopRatedMovies;
import io.reactivex.Flowable;

/**
 * Interface defined
 */

public interface APIHelper {

    Flowable<TopRatedMovies> getTopRatedMovies();

}
