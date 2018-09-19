package activity.nivedita.com.helloretrofit;

import activity.nivedita.com.helloretrofit.view.MVPView;
import activity.nivedita.com.model.TopRatedMovies;

/**
 * Created by NEETU on 06-03-2018.
 */

/*A basic contract interface which will be defined in the MVP pattern to decouple the architecture.*/
public interface MovieView extends MVPView {

    void getMoviesListSuccess(TopRatedMovies response);
    void noMoviesToDisplay();
    void addLoadingFooter(TopRatedMovies response);
    void removeLoadingFooter(TopRatedMovies response);
    void goBack();

}
