package activity.nivedita.movies.movies.presenter;

import android.util.Log;

import javax.inject.Inject;

import activity.nivedita.movies.data.DataManager;
import activity.nivedita.movies.model.tvshows.TvShows;
import activity.nivedita.movies.movies.view.TVShowsView;
import activity.nivedita.movies.networkutils.LogNetworkError;
import activity.nivedita.movies.networkutils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.PublishProcessor;

/**
 * The main tv shows presenter class to be initialized
 */

public class TVShowsPresenter<V extends TVShowsView> extends BasePresenter<V>
        implements TvShowsBasePresenter<V> {

    private DataManager mDataManager;
    private SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable;
    private PublishProcessor<Integer> publishProcessor;

    private static String TAG = TVShowsPresenter.class.getSimpleName();

    @Inject
    public TVShowsPresenter(DataManager mDataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable,
                            PublishProcessor<Integer> publishProcessor) {

        this.mDataManager = mDataManager;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
        this.publishProcessor = publishProcessor;
        this.schedulerProvider = schedulerProvider;
    }

    private Disposable getTVShows() {
        return mDataManager
                .getPopularTVShows(1)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui()).subscribe(new Consumer<TvShows>() {
                    @Override
                    public void accept(TvShows tvShows) throws Exception {

                        Log.i(TAG, "SERIALIZED RESPOSNSE IS" + tvShows.getResults().toString());
                        getMvpView().displayPopularTvShows(tvShows.getResults());
                        Log.i(TAG, "The poster path is" + tvShows.getResults().get(0).getPosterPath());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        getMvpView().showError(new LogNetworkError(throwable));
                    }
                });
    }

    @Override
    public void onFragmentViewInitialized() {

        //TODO: Test the loading of api from server.
        compositeDisposable.add(getTVShows());
    }
}
