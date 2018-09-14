package activity.nivedita.com.helloretrofit.presenter;

import android.support.v17.leanback.widget.*;

import activity.nivedita.com.di.scope.PerActivity;
import activity.nivedita.com.helloretrofit.MovieView;

/**
 * The base presenter class for movies.
 */

@PerActivity
public interface MoviesBasePresenter<V extends MovieView> extends Presenter<V> {

    void onViewInitialized();

}
