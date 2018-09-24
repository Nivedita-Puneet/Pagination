package activity.nivedita.movies.movies.presenter;

import activity.nivedita.movies.movies.MovieView;
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
