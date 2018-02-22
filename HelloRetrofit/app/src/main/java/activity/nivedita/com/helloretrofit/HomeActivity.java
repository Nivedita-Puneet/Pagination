package activity.nivedita.com.helloretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import java.util.List;

import activity.nivedita.com.model.Result;
import activity.nivedita.com.model.TopRatedMovies;
import activity.nivedita.com.networkutils.ConstantsUtil;
import activity.nivedita.com.networkutils.MovieApi;
import activity.nivedita.com.networkutils.MovieService;


import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class HomeActivity extends AppCompatActivity {

    private MovieService movieService;
    private static final int PAGE_START = 1;
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;
    private Subscription subscription = null;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        retrofit = MovieApi.getClient(this);
        movieService = retrofit.create(MovieService.class);
        getResultsList();
    }

    /*Retrofit with react native call to get results and top rated movies*/

    //TODO: Follow appropriate coding guide line to decouple the network logic from Main activity
    /*Add a presenter/ViewModel layer*/

    public Subscription getResultsList() {

        Observable<TopRatedMovies> resultObservable = movieService.getTopRatedMovies(ConstantsUtil.TMDB_API_KEY, "en_US", currentPage);
        return resultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TopRatedMovies>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(HomeActivity.class.getSimpleName(), e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(TopRatedMovies response) {

                        //TODO: set the results to the adapter.
                        List<Result> results = response.getResults();
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //subscription.unsubscribe();
    }

}
