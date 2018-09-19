package activity.nivedita.com.helloretrofit.presenter;

import javax.inject.Inject;

import activity.nivedita.com.data.DataManager;
import activity.nivedita.com.helloretrofit.view.MainMVPView;
import activity.nivedita.com.networkutils.rx.SchedulerProvider;

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
