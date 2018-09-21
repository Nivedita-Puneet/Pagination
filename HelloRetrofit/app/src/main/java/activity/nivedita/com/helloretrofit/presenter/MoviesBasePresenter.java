package activity.nivedita.com.helloretrofit.presenter;

import android.support.v17.leanback.widget.*;

import activity.nivedita.com.di.scope.PerActivity;
import activity.nivedita.com.helloretrofit.MovieView;
import io.reactivex.processors.PublishProcessor;

/**
 * The base presenter class for movies.
 */

public interface MoviesBasePresenter<V extends MovieView> extends Presenter<V> {

    void onViewInitialized();
    boolean getLoading();
    int getCurrentPage();
    PublishProcessor<Integer> getPublishProcessor();

    void setLoading(boolean loading);
    void setCurrentPage(int pageNumber);


}
