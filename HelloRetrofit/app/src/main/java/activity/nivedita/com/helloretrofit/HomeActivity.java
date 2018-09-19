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

import activity.nivedita.com.helloretrofit.presenter.MoviesBasePresenter;
import activity.nivedita.com.helloretrofit.presenter.MoviesPresenter;
import activity.nivedita.com.helloretrofit.view.MainMVPView;

import activity.nivedita.com.model.Result;
import activity.nivedita.com.model.TopRatedMovies;
import activity.nivedita.com.networkutils.ConstantsUtil;
import activity.nivedita.com.networkutils.LogNetworkError;


import activity.nivedita.com.networkutils.paginate.Paginateutil;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.PublishProcessor;


public class HomeActivity extends BaseActivity implements MovieView {

    @Inject
    MoviesBasePresenter<MovieView> moviesBasePresenter;

    @Inject
    MovieAdapter movieAdapter;

    /*Initialize views*/
    RecyclerView recyclerView;
    ProgressBar progressBar;

    private int lastVisibleItem, totalItemCount;


    @Inject
    LinearLayoutManager linearLayoutManager;

    private final int VISIBLE_THRESHOLD = 1;


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

        moviesBasePresenter.attachView(HomeActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(movieAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                 totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager
                        .findLastVisibleItemPosition();
                if(!moviesBasePresenter.getLoading() &&
                        (totalItemCount <= (lastVisibleItem+VISIBLE_THRESHOLD))){

                    int pageIndex =moviesBasePresenter.getCurrentPage();
                    pageIndex++;
                    moviesBasePresenter.setCurrentPage(pageIndex);

                    moviesBasePresenter.getPublishProcessor().onNext(pageIndex);
                    //movieAdapter.addLoadingFooter();
                    moviesBasePresenter.setLoading(true);
                }


            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();
        moviesBasePresenter.onViewInitialized();
    }

    @Override
    public void showError(LogNetworkError logNetworkError) {

        Toast.makeText(HomeActivity.this, logNetworkError.getAppErrorMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showWait() {

        progressBar.setVisibility(View.VISIBLE);
       // recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void removeWait() {

        progressBar.setVisibility(View.GONE);
       // recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void getMoviesListSuccess(TopRatedMovies response) {

        movieAdapter.addAll(response.getResults());
       // movieAdapter.notifyDataSetChanged();

    }

    @Override
    public void noMoviesToDisplay() {

    }

    @Override
    public void addLoadingFooter(TopRatedMovies response) {

        movieAdapter.setLoadingAdded(true);
        movieAdapter.addLoadingFooter(response.getResults().get(moviesBasePresenter.getCurrentPage()));
    }

    @Override
    public void removeLoadingFooter(TopRatedMovies response) {

    }

    @Override
    public void goBack() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        moviesBasePresenter.detachView();
    }
}
