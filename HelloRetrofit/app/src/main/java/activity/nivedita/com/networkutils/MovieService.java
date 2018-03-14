package activity.nivedita.com.networkutils;


import activity.nivedita.com.model.TopRatedMovies;
import io.reactivex.Flowable;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by NEETU on 21-02-2018.
 */
// Manipulate api requests as per the need.
public interface MovieService {

    /*Interface used to define the end points.*/
    @GET("top_rated")
    Flowable<TopRatedMovies> getTopRatedMovies(@Query("api_key") String apiKey,
                                                 @Query("language") String language,
                                                 @Query("page") int pageIndex);

    /*Helper class to create a service*/

    class Creator {
        public static MovieService newMovieAPI() {

            Retrofit retrofit = new Retrofit.Builder().client(buildClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(ConstantsUtil.BASE_URL).build();

            return retrofit.create(MovieService.class);

        }

        private static OkHttpClient buildClient() {
            return new OkHttpClient
                    .Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
    }
}
