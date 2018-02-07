package org.nivi.mydailynews.activity;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import org.nivi.mydailynews.R;
import org.nivi.mydailynews.adapter.NewsAdapter;
import org.nivi.mydailynews.model.Category;
import org.nivi.mydailynews.model.News;
import org.nivi.mydailynews.restapi.NewsAPI;
import org.nivi.mydailynews.restapi.NewsAPIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeControls();

        NewsAPI apiService =
                NewsAPIClient.getClient().create(NewsAPI.class);


        Call<News> call = apiService.getUpdatedNews();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                News news = response.body();
                List<Category> categories = news.getCategories();
                recyclerView.setAdapter(new NewsAdapter(MainActivity.this, categories));
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }

    private void initializeControls() {

        recyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);
        progressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);


    }
}
