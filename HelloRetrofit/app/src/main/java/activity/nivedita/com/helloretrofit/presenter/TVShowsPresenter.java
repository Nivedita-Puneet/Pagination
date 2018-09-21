package activity.nivedita.com.helloretrofit.presenter;

import javax.inject.Inject;

import activity.nivedita.com.data.DataManager;
import activity.nivedita.com.helloretrofit.view.TVShowsView;
import activity.nivedita.com.networkutils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
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

    @Override
    public void onFragmentViewInitialized() {

        //TODO: Load the TV shows from rest api.
    }
}