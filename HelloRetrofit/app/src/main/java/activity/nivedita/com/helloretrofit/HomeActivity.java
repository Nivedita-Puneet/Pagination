package activity.nivedita.com.helloretrofit;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;

import java.util.List;

import javax.inject.Inject;

import activity.nivedita.com.helloretrofit.presenter.MoviesPresenter;
import activity.nivedita.com.helloretrofit.view.MainMVPView;

import activity.nivedita.com.model.Result;
import activity.nivedita.com.model.TopRatedMovies;
import activity.nivedita.com.networkutils.ConstantsUtil;
import activity.nivedita.com.networkutils.LogNetworkError;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.PublishProcessor;


public class HomeActivity extends BaseActivity implements MainMVPView {

    @Inject
    MoviesPresenter mMoviesPresenter;

    @Inject
    MovieAdapter movieAdapter;

    /*Initialize views*/
    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager linearLayoutManager;

    /*Classes used to add pagination */

    private static String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getActivityComponent().inject(HomeActivity.this);
        initializeControls();
    }

    private void initializeControls() {

        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(movieAdapter);
        mMoviesPresenter.attachView(this);

        recyclerView.addOnScrollListener(new MoviePagination(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage, int totalItemCount, View view) {

                if (!mMoviesPresenter.proceedPagination()) {

                    mMoviesPresenter.getPagination().onNext(movieAdapter.getLastVisibleItemId());
                }

            }
        });

        mMoviesPresenter.loadMovies();
    }


    @Override
    public void showMovies(List<Result> movies) {

        movieAdapter.addAll(movies);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoviesEmpty() {

        Toast.makeText(HomeActivity.this, "Movies you are looking for are not available", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(LogNetworkError logNetworkError) {

        Toast.makeText(HomeActivity.this, logNetworkError.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showWait() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {

        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mMoviesPresenter.detachView();
    }
}
