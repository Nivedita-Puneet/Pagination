package activity.nivedita.com.networkutils;

import android.content.Context;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NEETU on 21-02-2018.
 */

//class to build our retrofit service
public class MovieApi {

    //TODO: Need to refactor the class using Dagger2 to decouple it and make it Reusable.
    public static Retrofit retrofit = null;

    private MovieApi() {

    }

    public static MovieService createMovieService() {

        Retrofit.Builder builder = new Retrofit.Builder().client(buildClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(ConstantsUtil.BASE_URL);

        return builder.build().create(MovieService.class);
    }

    //Ok http cleint to log the network responses.
    private static OkHttpClient buildClient() {
        return new OkHttpClient
                .Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }
}
