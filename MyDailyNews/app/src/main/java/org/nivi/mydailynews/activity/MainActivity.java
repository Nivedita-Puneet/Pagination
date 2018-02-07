package org.nivi.mydailynews.activity;

import android.content.Context;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.nivi.mydailynews.R;
import org.nivi.mydailynews.adapter.NewsAdapter;
import org.nivi.mydailynews.model.Category;
import org.nivi.mydailynews.model.News;
import org.nivi.mydailynews.restapi.NewsAPI;
import org.nivi.mydailynews.restapi.NewsAPIClient;
import org.nivi.mydailynews.util.DividerItemDecoration;
import org.nivi.mydailynews.util.VerticalSpaceItemDecoration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by NEETU on 08-02-2018.
 */
public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private String actionBarTitle;
    private NewsAPI apiService;
    private static final int VERTICAL_ITEM_SPACE = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeControls();

        apiService =
                NewsAPIClient.getClient().create(NewsAPI.class);

        if (!checkNetworkConnectivity()) {

            Toast.makeText(MainActivity.this, "Review your network settings", Toast.LENGTH_LONG).show();
        } else {
            getNews(apiService);
        }


    }

    private void getNews(NewsAPI apiService) {
        Call<News> call = apiService.getUpdatedNews();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                News news = response.body();
                actionBarTitle = news.getTitle();
                getSupportActionBar().setTitle(actionBarTitle);
                List<Category> categories = news.getCategories();
                recyclerView.setAdapter(new NewsAdapter(MainActivity.this, categories));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    public void onResume() {
        super.onResume();
        if (checkNetworkConnectivity()) {
            getNews(apiService);
        }

    }

    private void initializeControls() {

        recyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);
        progressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        progressBar.setVisibility(View.VISIBLE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, R.drawable.divider));

    }

    private boolean checkNetworkConnectivity() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
