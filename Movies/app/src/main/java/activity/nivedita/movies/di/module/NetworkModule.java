package activity.nivedita.movies.di.module;

import javax.inject.Singleton;

import activity.nivedita.movies.movies.BuildConfig;
import activity.nivedita.movies.networkutils.ConstantsUtil;
import activity.nivedita.movies.networkutils.MovieService;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The network module provided to define dependencies for api service.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {

        if (BuildConfig.DEBUG) {

            return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE);
        }
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {

        return new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).build();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofitBuilder() {

        return new Retrofit.Builder().
                client(provideOkHttpClient())
                .addConverterFactory(provideGsonConverterFactory())
                .addCallAdapterFactory(provideRxJava2CallAdapterFactory())
                .baseUrl(ConstantsUtil.BASE_URL).build();
    }

    @Provides
    @Singleton
    MovieService provideMovieAPI() {

        return provideRetrofitBuilder().create(MovieService.class);
    }
}
