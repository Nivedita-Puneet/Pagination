package activity.nivedita.com.helloretrofit.presenter;

import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import activity.nivedita.com.data.DataManager;

import activity.nivedita.com.helloretrofit.view.MainMVPView;
import activity.nivedita.com.model.Pager;
import activity.nivedita.com.model.Result;
import activity.nivedita.com.model.TopRatedMovies;
import activity.nivedita.com.networkutils.LogNetworkError;
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
 * Created by NEETU on 05-03-2018.
 */

public class MoviesPresenter extends BasePresenter<MainMVPView> {

    private final DataManager mDataManager;
    private CompositeDisposable compositeDisposable;
    private boolean isRequestOnWay;

    private PublishProcessor<Integer> pagination;

    @Inject
    public MoviesPresenter(DataManager mDataManager, boolean isRequestOnWay, PublishProcessor<Integer> pagination) {
        this.mDataManager = mDataManager;
        this.isRequestOnWay = isRequestOnWay;
        this.pagination = pagination;

    }

    @Override
    public void attachView(MainMVPView mvpView) {
        super.attachView(mvpView);
        // Initializes variables
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        //compositeDisposable.add(getDisposable(returnPublishProcessorInstance()));
        compositeDisposable.add(getDisposable());
        // PublishSubject using switchMap to invoke services to the API.
    }

    /* private Disposable getMovies() {

         return sendRequestToApiObservable().subscribe(new Consumer<TopRatedMovies>() {
             @Override
             public void accept(TopRatedMovies topRatedMovies) throws Exception {

                 List<Result> results = topRatedMovies.getResults();
                 if (!results.isEmpty()) {
                     getMvpView().showMovies(results);
                 } else {

                     getMvpView().showMoviesEmpty();
                 }
             }


         }, new Consumer<Throwable>() {
             @Override
             public void accept(Throwable throwable) throws Exception {

                 getMvpView().showError(new LogNetworkError(throwable));
             }
         });
     }*/
    private Disposable getDisposable() {

        Disposable disposable = null;
        disposable = pagination.onBackpressureDrop().doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                isRequestOnWay = true;
                getMvpView().showWait();
            }
        }).concatMap(new Function<Integer, Publisher<TopRatedMovies>>() {
            @Override
            public Publisher<TopRatedMovies> apply(Integer integer) throws Exception {

                return sendRequestToApiObservable();
            }
        }).doOnNext(new Consumer<TopRatedMovies>() {
            @Override
            public void accept(TopRatedMovies topRatedMoviesResponse) throws Exception {
                List<Result> movies = topRatedMoviesResponse.getResults();

                if (movies.isEmpty()) {
                    getMvpView().showMoviesEmpty();
                } else {

                    getMvpView().showMovies(movies);
                }

                isRequestOnWay = false;
                getMvpView().removeWait();
            }
        }).doOnError(new Consumer<Throwable>() {

            @Override
            public void accept(Throwable throwable) throws Exception {
                getMvpView().showError(new LogNetworkError(throwable));
            }
        }).onErrorResumeNext(new Function<Throwable, Publisher<? extends TopRatedMovies>>() {
            @Override
            public Flowable<? extends TopRatedMovies> apply(Throwable throwable) throws Exception {

                return Flowable.error(new LogNetworkError(throwable));

            }
        }).subscribe();

        return disposable;

    }

    private Flowable<TopRatedMovies> sendRequestToApiObservable() {
        return mDataManager.getListOfTopRatedMovies().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void detachView() {
        super.detachView();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public void loadMovies() {
        checkViewAttached();
        pagination.onNext(0);

    }

    public PublishProcessor<Integer> getPagination() {
        return pagination;
    }

    public void setPagination(PublishProcessor<Integer> pagination) {
        this.pagination = pagination;
    }

    public boolean proceedPagination() {
        return isRequestOnWay;
    }
}
