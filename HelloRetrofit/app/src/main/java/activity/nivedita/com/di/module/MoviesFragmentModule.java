package activity.nivedita.com.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import activity.nivedita.com.di.scope.ActivityContext;
import activity.nivedita.com.di.scope.ApplicationContext;
import activity.nivedita.com.di.scope.PerActivity;
import activity.nivedita.com.helloretrofit.MovieAdapter;
import activity.nivedita.com.helloretrofit.MovieView;
import activity.nivedita.com.helloretrofit.MoviesFragment;
import activity.nivedita.com.helloretrofit.presenter.MoviesBasePresenter;
import activity.nivedita.com.helloretrofit.presenter.MoviesPresenter;
import activity.nivedita.com.networkutils.rx.AppSchedulerprovider;
import activity.nivedita.com.networkutils.rx.SchedulerProvider;
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
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerprovider();
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
    @PerActivity
    MoviesBasePresenter<MovieView> provideSunshinePresenter(MoviesPresenter<MovieView> moviesPresenter) {
        return moviesPresenter;
    }
}
