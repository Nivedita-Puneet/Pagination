package org.nivi.mydailynews.restapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NEETU on 07-02-2018.
 */

/**
 * Class uses Retrofit to make an API call and get the results.
 */

public class NewsAPIClient {
    public static final String BASE_URL = "https://dl.dropboxusercontent.com/s/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
