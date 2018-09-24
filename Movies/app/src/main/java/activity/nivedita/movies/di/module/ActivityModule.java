package activity.nivedita.movies.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import activity.nivedita.movies.di.scope.ActivityContext;
import activity.nivedita.movies.di.scope.PerActivity;
import activity.nivedita.movies.movies.MovieAdapter;
import activity.nivedita.movies.movies.MovieView;
import activity.nivedita.movies.movies.presenter.MoviesBasePresenter;
import activity.nivedita.movies.movies.presenter.MoviesPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by NEETU on 08-03-2018.
 */

@Module
public class ActivityModule {

    private Activity mActivity;
    private int currentPage = 1;
    private boolean loading = false;

    public ActivityModule(Activity activity) {

        this.mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    MovieAdapter provideMoviesAdapter() {
        return new MovieAdapter(mActivity, getLoading());
    }

    @Provides
    LinearLayoutManager linearLayoutManager() {
        return new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
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
    PublishProcessor<Integer> publishProcessor() {
        return PublishProcessor.create();
    }

    @Provides
    CompositeDisposable getCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    MoviesBasePresenter<MovieView> provideSunshinePresenter(MoviesPresenter<MovieView> moviesPresenter) {
        return moviesPresenter;
    }


}
