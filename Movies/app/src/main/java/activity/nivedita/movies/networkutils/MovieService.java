package activity.nivedita.movies.networkutils;


import activity.nivedita.movies.model.TopRatedMovies;
import activity.nivedita.movies.model.tvshows.TvShows;
import io.reactivex.Flowable;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * interface defined to create the movie service api.
 */
// Manipulate api requests as per the need.
public interface MovieService {

    /*Interface used to define the end points.*/
    @GET("movie/top_rated")
    Flowable<TopRatedMovies> getTopRatedMovies(@Query("api_key") String apiKey,
                                               @Query("language") String language,
                                               @Query("page") int pageIndex);

    @GET("tv/popular")
    Flowable<TvShows> getPopularTVShows(@Query("api_key") String apiKey,
                                        @Query("language") String language,
                                        @Query("page") int pageIndex);
    /*Helper class to create a service*/

    /*class Creator {
        public static MovieService newMovieAPI() {

            Retrofit retrofit = new Retrofit.Builder().client(buildClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(ConstantsUtil.BASE_URL).build();

            return retrofit.create(MovieService.class);

        }

        private static OkHttpClient buildClient() {
            return new OkHttpClient
                    .Builder()
                    .addInterceptor(new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        }
    }*/


}
