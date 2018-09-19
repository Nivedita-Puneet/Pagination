package activity.nivedita.com.helloretrofit.presenter;

import activity.nivedita.com.di.scope.PerActivity;
import activity.nivedita.com.helloretrofit.view.MainMVPView;

/**
 * The main base presenter to be attached to the view when initialized
 */

@PerActivity
public interface MainActivityBasePresenter<V extends MainMVPView> extends Presenter<V> {

    void onMainActivityViewInitialized();
}
