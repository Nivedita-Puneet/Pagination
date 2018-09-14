package activity.nivedita.com.helloretrofit.presenter;

import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import activity.nivedita.com.data.DataManager;

import activity.nivedita.com.helloretrofit.MovieView;
import activity.nivedita.com.helloretrofit.view.MainMVPView;
import activity.nivedita.com.model.Pager;
import activity.nivedita.com.model.Result;
import activity.nivedita.com.model.TopRatedMovies;
import activity.nivedita.com.networkutils.LogNetworkError;
import activity.nivedita.com.networkutils.paginate.Paginateutil;
import activity.nivedita.com.networkutils.rx.SchedulerProvider;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Movies presenter class to be used by main activity class.
 */

public class MoviesPresenter<V extends MovieView> extends BasePresenter<V>
        implements MoviesBasePresenter<V> {

    private static final String TAG = MoviesPresenter.class.getSimpleName();
    public static Paginateutil paginateutil;
    private int currentPage;
    private boolean loading;

    @Inject
    public MoviesPresenter(DataManager mDataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable,
                           PublishProcessor<Integer> publishProcessor) {

        super(mDataManager, schedulerProvider, compositeDisposable, publishProcessor);

    }


    private Disposable getPopularMovies() {


        getMvpView().showWait();
        return getPublishProcessor().onBackpressureDrop()
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        getMvpView().showWait();
                    }
                }).concatMap(new Function<Integer, Publisher<TopRatedMovies>>() {
                    @Override
                    public Publisher<TopRatedMovies> apply(Integer integer) throws Exception {
                        return getDataManager().getTopRatedMovies()
                                .subscribeOn(getSchedulerProvider().io()).observeOn(getSchedulerProvider().ui());
                    }
                }).subscribe(new Consumer<TopRatedMovies>() {
                    @Override
                    public void accept(TopRatedMovies topRatedMovies) throws Exception {
                        getMvpView().removeWait();
                        getMvpView().getMoviesListSuccess(topRatedMovies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        getMvpView().showError(new LogNetworkError(throwable));
                    }
                });
    }


    @Override
    public void onViewInitialized() {

        getCompositeDisposable().add(getPopularMovies());
        setCurrentPage(getCurrentPage());
        getPublishProcessor().onNext(getCurrentPage());
    }

}
