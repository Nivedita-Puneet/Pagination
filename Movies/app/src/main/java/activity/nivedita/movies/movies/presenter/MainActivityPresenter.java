package activity.nivedita.movies.movies.presenter;

import javax.inject.Inject;

import activity.nivedita.movies.data.DataManager;
import activity.nivedita.movies.movies.view.MainMVPView;
import activity.nivedita.movies.networkutils.rx.SchedulerProvider;

/**
 * The main presenter to be injected to view pager activity
 */

public class MainActivityPresenter<V extends MainMVPView> extends BasePresenter<V>
        implements MainActivityBasePresenter<V> {

    @Inject
    public MainActivityPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider) {

    }

    @Override
    public void onMainActivityViewInitialized() {


    }
}
