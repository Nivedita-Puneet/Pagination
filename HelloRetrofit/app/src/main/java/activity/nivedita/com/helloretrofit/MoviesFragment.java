package activity.nivedita.com.helloretrofit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import activity.nivedita.com.base.BaseFragment;
import activity.nivedita.com.model.TopRatedMovies;
import activity.nivedita.com.networkutils.LogNetworkError;

/**
 * Fragment to be attached in the first tab
 */

public class MoviesFragment extends BaseFragment implements MovieView {

    private static String TAG = MoviesFragment.class.getSimpleName();

    public static MoviesFragment newInstance() {

        Bundle args = new Bundle();
        MoviesFragment moviesFragment = new MoviesFragment();
        moviesFragment.setArguments(args);
        return moviesFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.movies_fragment, container, false);
    }

    @Override
    public void showError(LogNetworkError logNetworkError) {

    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void getMoviesListSuccess(TopRatedMovies response) {

    }

    @Override
    public void noMoviesToDisplay() {

    }

    @Override
    public void addLoadingFooter(TopRatedMovies response) {

    }

    @Override
    public void removeLoadingFooter(TopRatedMovies response) {

    }

    @Override
    public void goBack() {


    }
}
