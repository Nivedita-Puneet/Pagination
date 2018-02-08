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
import org.nivi.mydailynews.util.InfiniteScrollViewListener;
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
    private NewsAPI loadMoreService;
    private static final int VERTICAL_ITEM_SPACE = 7;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeControls();

        /*Creating one more instance of NewsAPi in order to handle and demonstrate Refresh functionality.*/
        loadMoreService = NewsAPIClient.getClient().create(NewsAPI.class);
        apiService =
                NewsAPIClient.getClient().create(NewsAPI.class);

        if (!checkNetworkConnectivity()) {

            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, getString(R.string.review_network_settings), Toast.LENGTH_LONG).show();
        } else {
            getNews(apiService);
            recyclerView.addOnScrollListener(new InfiniteScrollViewListener() {
                @Override
                public void onLoadMore() {

                    progressBar.setVisibility(View.VISIBLE);
                    getNews(loadMoreService);
                }
            });
        }


    }

    /*Method to invoke the news and bind it to UI. Used Retrofit client and GSON libraries to perform the same.*/
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

    @Override
    public void onResume() {
        super.onResume();
        if (checkNetworkConnectivity()) {
            getNews(apiService);
        }

    }


    // Initialize all the controls in order to invoke the activity.
    private void initializeControls() {

        recyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);
        progressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        progressBar.setVisibility(View.VISIBLE);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

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