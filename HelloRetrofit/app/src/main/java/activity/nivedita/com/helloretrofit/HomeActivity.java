package activity.nivedita.com.helloretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;


import org.reactivestreams.Publisher;

import java.util.List;

import activity.nivedita.com.model.Result;
import activity.nivedita.com.model.TopRatedMovies;
import activity.nivedita.com.networkutils.ConstantsUtil;
import activity.nivedita.com.networkutils.MovieApi;
import activity.nivedita.com.networkutils.MovieService;


import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;


public class HomeActivity extends AppCompatActivity {

    private MovieService movieService;

    /*Initialize views*/
    RecyclerView recyclerView;
    ProgressBar progressBar;
    MovieAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    /*Classes used to add pagination */
    private PublishProcessor<Integer> pagination;
    private CompositeDisposable compositeDisposable;
    private boolean requestOnWay = false;

    private static String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeControls();
    }

    private void initializeControls() {

        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);

        adapter = new MovieAdapter(this);
        pagination = PublishProcessor.create();
        compositeDisposable = new CompositeDisposable();
        movieService = MovieApi.createMovieService();

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new MoviePagination(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage, int totalItemCount, View view) {

                if (!requestOnWay) {
                    pagination.onNext(adapter.getLastVisibleItemId());
                }
            }
        });

        compositeDisposable.add(getMovieResults());
        pagination.onNext(0);
    }

    //TODO: add all the composite disposables into the data layer and define a clean mvp architecture.
    /*TODO: 1. define a data model and add all the network related operations into that package.
    * TODO: 2. Define a base view contract and Base presenter and define a main presenter.
    *TODO:  3. Create a clean dependency injection pattern.*/
    private Disposable getMovieResults() {

        Disposable disposable = pagination.onBackpressureDrop().concatMap(new Function<Integer, Publisher<TopRatedMovies>>() {
            @Override
            public Publisher<TopRatedMovies> apply(@NonNull Integer page) throws Exception {
                return getListOfTopRatedMovies();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<TopRatedMovies>() {
                    @Override
                    public void accept(TopRatedMovies topRatedMoviesResponse) throws Exception {
                        List<Result> movies = topRatedMoviesResponse.getResults();
                        adapter.addAll(movies);
                        progressBar.setVisibility(View.INVISIBLE);
                        requestOnWay = false;
                    }
                }).subscribe();

        return disposable;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        compositeDisposable.dispose();
    }

    private Flowable<TopRatedMovies> getListOfTopRatedMovies() {

        return movieService.getTopRatedMovies(ConstantsUtil.TMDB_API_KEY,
                "en_US",
                ConstantsUtil.TOTAL_PAGES)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
