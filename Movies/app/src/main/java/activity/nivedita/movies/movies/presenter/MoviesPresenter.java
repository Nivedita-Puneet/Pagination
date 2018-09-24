package activity.nivedita.movies.movies.presenter;

import org.reactivestreams.Publisher;

import javax.inject.Inject;

import activity.nivedita.movies.data.DataManager;

import activity.nivedita.movies.movies.MovieView;
import activity.nivedita.movies.model.TopRatedMovies;
import activity.nivedita.movies.networkutils.ConstantsUtil;
import activity.nivedita.movies.networkutils.LogNetworkError;
import activity.nivedita.movies.networkutils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.PublishProcessor;

/**
 * Movies presenter class to be used by main activity class.
 */

public class MoviesPresenter<V extends MovieView> extends BasePresenter<V>
        implements MoviesBasePresenter<V> {

    private static final String TAG = MoviesPresenter.class.getSimpleName();

    private int currentPage;
    private boolean loading;
    private DataManager mDataManager;
    private SchedulerProvider schedulerProvider;

    private PublishProcessor<Integer> publishProcessor;
    private CompositeDisposable compositeDisposable;

    @Inject
    public MoviesPresenter(DataManager mDataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable,
                           PublishProcessor<Integer> publishProcessor,
                           int currentPage,
                           boolean loading) {

        this.mDataManager = mDataManager;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
        this.publishProcessor = publishProcessor;
        this.schedulerProvider = schedulerProvider;
        this.currentPage = currentPage;
        this.loading = loading;

    }

    private Disposable getPopularMovies() {


        getMvpView().showWait();
        return publishProcessor.onBackpressureDrop()
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {



                    }
                }).concatMap(new Function<Integer, Publisher<TopRatedMovies>>() {
                    @Override
                    public Publisher<TopRatedMovies> apply(Integer integer)
                            throws Exception {

                        MoviesPresenter.this.loading = true;
                        getMvpView().showWait();
                        return mDataManager.getTopRatedMovies(currentPage)
                                .subscribeOn(schedulerProvider.io())
                                .observeOn(schedulerProvider.ui());
                    }
                }).subscribe(new Consumer<TopRatedMovies>() {
                    @Override
                    public void accept(TopRatedMovies topRatedMovies) throws Exception {

                        MoviesPresenter.this.loading = false;
                        getMvpView().removeWait();
                        getMvpView().getMoviesListSuccess(topRatedMovies);

                        if(currentPage <= ConstantsUtil.TOTAL_PAGES){
                            getMvpView().addLoadingFooter(topRatedMovies);
                        }
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

        compositeDisposable.add(getPopularMovies());
        publishProcessor.onNext(currentPage);
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeDisposable.clear();

    }

    public boolean getLoading() {
        return MoviesPresenter.this.loading;
    }

    public int getCurrentPage() {
        return MoviesPresenter.this.currentPage;
    }

    public PublishProcessor<Integer> getPublishProcessor() {
        return MoviesPresenter.this.publishProcessor;
    }

    public void setLoading(boolean loading){
        this.loading = loading;
    }

    public void setCurrentPage(int currentPage){
       this.currentPage = currentPage;
    }
}
