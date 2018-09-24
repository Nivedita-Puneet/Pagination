package activity.nivedita.movies.movies.presenter;

import activity.nivedita.movies.di.scope.PerActivity;
import activity.nivedita.movies.movies.view.MainMVPView;

/**
 * The main base presenter to be attached to the view when initialized
 */

@PerActivity
public interface MainActivityBasePresenter<V extends MainMVPView> extends Presenter<V> {

    void onMainActivityViewInitialized();
}
