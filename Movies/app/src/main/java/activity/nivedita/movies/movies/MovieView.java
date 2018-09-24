package activity.nivedita.movies.movies;

import activity.nivedita.movies.movies.view.MVPView;
import activity.nivedita.movies.model.TopRatedMovies;

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
