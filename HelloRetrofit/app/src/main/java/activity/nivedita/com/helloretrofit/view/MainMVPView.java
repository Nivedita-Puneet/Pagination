package activity.nivedita.com.helloretrofit.view;

import java.util.List;

import activity.nivedita.com.model.Result;
import activity.nivedita.com.model.TopRatedMovies;
import activity.nivedita.com.networkutils.LogNetworkError;

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
