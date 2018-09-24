package activity.nivedita.movies.movies.view;

import java.util.List;

import activity.nivedita.movies.model.Result;
import activity.nivedita.movies.networkutils.LogNetworkError;

/**
 * Created by PUNEETU on 09-03-2018.
 */

public interface MainMVPView extends MVPView {

    void showMovies(List<Result> movies);

    void showMoviesEmpty();

    void showError(LogNetworkError logNetworkError);

    void showWait();

    void removeWait();


    // int totalItemsShowed();

}
