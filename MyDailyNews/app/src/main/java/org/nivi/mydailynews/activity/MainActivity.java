package org.nivi.mydailynews.activity;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.nivi.mydailynews.R;
import org.nivi.mydailynews.model.News;
import org.nivi.mydailynews.restapi.NewsAPI;
import org.nivi.mydailynews.restapi.NewsAPIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NewsAPI apiService =
                NewsAPIClient.getClient().create(NewsAPI.class);

        Call<News> call = apiService.getUpdatedNews();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                News news = response.body();
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }
}
