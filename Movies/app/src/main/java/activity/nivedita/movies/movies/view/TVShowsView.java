package activity.nivedita.movies.movies.view;

import java.util.List;

import activity.nivedita.movies.model.tvshows.PopularTvShows;

/**
 * The view defines the tv shows.
 */

public interface TVShowsView extends MVPView{

    void viewTVShows();
    void showWait();
    void removeWait();
    void displayPopularTvShows(List<PopularTvShows> popularTvShowsList);

}
