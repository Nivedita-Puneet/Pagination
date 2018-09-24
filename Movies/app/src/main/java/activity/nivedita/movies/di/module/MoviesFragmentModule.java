package activity.nivedita.movies.di.module;

import android.support.v7.widget.LinearLayoutManager;

import activity.nivedita.movies.movies.MovieAdapter;
import activity.nivedita.movies.movies.MovieView;
import activity.nivedita.movies.movies.MoviesFragment;
import activity.nivedita.movies.movies.presenter.MoviesBasePresenter;
import activity.nivedita.movies.movies.presenter.MoviesPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.PublishProcessor;

/**
 * Fragment module to be defined and injected into Fragment provider for movie
 */
@Module
public class MoviesFragmentModule {

    private int currentPage = 1;
    private boolean loading = false;

    @Provides
    PublishProcessor<Integer> publishProcessor() {
        return PublishProcessor.create();
    }

    @Provides
    CompositeDisposable getCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    MovieAdapter provideMoviesAdapter(MoviesFragment moviesFragment) {
        return new MovieAdapter(moviesFragment.getActivity(), getLoading());
    }

    @Provides
    LinearLayoutManager linearLayoutManager(MoviesFragment moviesFragment) {
        return new LinearLayoutManager(moviesFragment.getActivity(),
                LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    int getCurrentPage() {
        return currentPage;
    }

    @Provides
    boolean getLoading() {
        return loading;
    }

    @Provides
    MoviesBasePresenter<MovieView> provideSunshinePresenter(MoviesPresenter<MovieView> moviesPresenter) {
        return moviesPresenter;
    }
}
