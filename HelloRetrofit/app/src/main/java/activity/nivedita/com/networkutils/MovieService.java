package activity.nivedita.com.networkutils;


import activity.nivedita.com.model.Result;

import activity.nivedita.com.model.TopRatedMovies;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by NEETU on 21-02-2018.
 */
// Manipulate api requests as per the need.
public interface MovieService {

    /*Interface used to define the end points.*/
    @GET("top_rated")
    Observable<TopRatedMovies> getTopRatedMovies(@Query("api_key") String apiKey,
                                                 @Query("language") String language,
                                                 @Query("page") int pageIndex);
}
